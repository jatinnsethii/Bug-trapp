package com.msit.jatin.main.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msit.jatin.main.dao.BugDao;
import com.msit.jatin.main.dao.ComponentDao;
import com.msit.jatin.main.dao.ProductDao;
import com.msit.jatin.main.dto.BugDto;
import com.msit.jatin.main.dto.BugResponse;
import com.msit.jatin.main.exceptions.BugTrackingException;
import com.msit.jatin.main.model.Bug;
import com.msit.jatin.main.model.Component;
import com.msit.jatin.main.model.NotificationEmail;
import com.msit.jatin.main.model.User;

@Service
public class BugService {
	private final BugDao bugDao;
	private final ComponentDao componentDao;
	private final ProductDao productDao;
	private final AuthService authService;
	private final MailService mailService;

	public BugService(BugDao bugDao, ComponentDao componentDao, ProductDao productDao, AuthService authService,
			MailService mailService) {
		this.bugDao = bugDao;
		this.componentDao = componentDao;
		this.productDao = productDao;
		this.authService = authService;
		this.mailService = mailService;
	}
	@Transactional
	public BugDto addBug(BugDto bugDto) {

		Component comp = componentDao.findByComponentName(bugDto.getCompName())
				.orElseThrow(() -> new BugTrackingException(
						"Bug cannot be registered because no component found with name: " + bugDto.getCompName()));
		Bug x = bugDao.findByBugDetails(bugDto.getBugDetails()).orElse(x=null);
		if(x==null) {
			Bug b = bugDao.save(mapDtoToBug(bugDto, comp));
			mailService.sendMail(new NotificationEmail("Bug Reported", authService.getCurrentUser().getEmail(),
					"Thankyou for reporting the bug." + " Here's the link to track your progress: "
							+ "http://localhost:8080/bugapi/bug/" + b.getBugDetails()));
			bugDto.setId(b.getBug_id());
		}else {
			throw new IllegalArgumentException("Bug already Registered");
		}
		
		return bugDto;
	}

	private Bug mapDtoToBug(BugDto bugDto, Component comp) {
		Bug c = new Bug();
		c.setBugDetails(bugDto.getBugDetails());
		c.setDescription(bugDto.getDescription());
		c.setComp(comp);
		c.setProd(comp.getProd());
		c.setCreatedDate(Date.from(Instant.now()));
		c.setUpdatedTime(Date.from(Instant.now()));
		c.setUser(authService.getCurrentUser());
		c.setStatus("Unresolved");
		return c;
	}

	private BugResponse mapBugToDto(Bug bug) {
		BugResponse c = new BugResponse();
		c.setBugDetails(bug.getBugDetails());
		c.setDescription(bug.getDescription());
		c.setCompName(bug.getComp().getComponentName());
		c.setProductName(bug.getProd().getProductName());
		c.setCreatedTime(bug.getCreatedDate().toString());
		c.setUpdatedTime(bug.getUpdatedTime().toString());
		c.setOwner(bug.getUser().getEmail());
		c.setStatus(bug.getStatus());
		return c;
	}

	@Transactional(readOnly = true)
	public List<BugResponse> getAllBugs() {
		List<Bug> al = bugDao.findAll();
		List<BugResponse> ans = new ArrayList<BugResponse>();
		for (Bug x : al) {
			ans.add(mapBugToDto(x));
		}
		return ans;
	}

	@Transactional(readOnly = true)
	public BugResponse getBug(String bugName) {
		Bug bug = bugDao.findByBugDetails(bugName)
				.orElseThrow(() -> new BugTrackingException("No bug registered with name:" + bugName));

		return mapBugToDto(bug);
	}

	@Transactional
	public String deleteBug(String bugName) {
		Bug bug = bugDao.findByBugDetails(bugName)
				.orElseThrow(() -> new BugTrackingException("No bug registered with name:" + bugName));
		bugDao.delete(bug);
		return "Bug Deleted";
	}

	@Transactional
	public List<String> getMyBugs() {
		User user = authService.getCurrentUser();
		List<Bug> al = bugDao.findByUser(user);
		List<String> ans = new ArrayList<String>();
		for (Bug x : al) {
			ans.add(x.getBugDetails());
		}
		return ans;
	}

	@Transactional
	public List<String> getMyBugsUrl() {
		User user = authService.getCurrentUser();
		List<Bug> al = bugDao.findByUser(user);
		List<String> ans = new ArrayList<String>();
		for (Bug x : al) {
			ans.add("http://localhost:8080/bugapi/bug/" + x.getBugDetails());
		}
		return ans;
	}
	@Transactional
	public String resolveBug(String bugName) {
		Bug b = bugDao.findByBugDetails(bugName).orElseThrow(()-> 
		new IllegalArgumentException("No bug found with name: "+bugName));
		b.setStatus("Resolved");
		b.setUpdatedTime(Date.from(Instant.now()));
		bugDao.save(b);
		mailService.sendMail(new NotificationEmail("Bug Resolved", authService.getCurrentUser().getEmail(),
				"The bug you reported has been resolved." + " Here's the link to track your progress: "
						+ "http://localhost:8080/bugapi/bug/" + b.getBugDetails()));
		return "Status updated";
		
	}

}
