package com.msit.jatin.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msit.jatin.main.dao.BugDao;
import com.msit.jatin.main.dao.ComponentDao;
import com.msit.jatin.main.dao.ProductDao;
import com.msit.jatin.main.dto.ComponentDto;
import com.msit.jatin.main.exceptions.BugTrackingException;
import com.msit.jatin.main.exceptions.ElementNotFoundException;
import com.msit.jatin.main.model.Bug;
import com.msit.jatin.main.model.Component;
import com.msit.jatin.main.model.Product;

@Service
public class ComponentService {
	private final ComponentDao componentDao;
	private final ProductDao productDao;
	private final BugDao bugDao;

	public ComponentService(ComponentDao componentDao, ProductDao productDao, BugDao bugDao) {
		this.componentDao = componentDao;
		this.productDao = productDao;
		this.bugDao = bugDao;
	}

	@Transactional
	public ComponentDto saveComponent(ComponentDto componentDto) {
		
		Product p = productDao.findByProductName(componentDto.getProductName()).orElseThrow(
				() -> new ElementNotFoundException("Cannot register component because no product found with name: "
						+ componentDto.getProductName()));
		Component ref = componentDao.findByComponentName(componentDto.getName()).orElse(ref=null);
		if(ref==null) {
			Component c = componentDao.save(mapDtoToComponent(componentDto, p));
			componentDto.setId(c.getId());
		}else {
			throw new IllegalArgumentException("Component already exists");
		}
		
		return componentDto;
	}

	private Component mapDtoToComponent(ComponentDto componentDto, Product p) {
		Component c = new Component();
		c.setComponentName(componentDto.getName());
		c.setComponentDesc(componentDto.getDesc());
		c.setProd(p);
		return c;
	}

	private ComponentDto mapComponentToDto(Component component) {
		ComponentDto c = new ComponentDto();
		c.setId(component.getId());
		c.setName(component.getComponentName());
		c.setDesc(component.getComponentDesc());
		c.setProductName(component.getProd().getProductName());
		return c;
	}

	@Transactional(readOnly = true)
	public List<ComponentDto> getAll() {
		List<Component> cl = componentDao.findAll();
		List<ComponentDto> ans = new ArrayList<>();
		for (Component c : cl) {
			ans.add(mapComponentToDto(c));
		}
		return ans;
	}

	@Transactional(readOnly = true)
	public ComponentDto getComponentByName(String componentName) {
		Component c = componentDao.findByComponentName(componentName)
				.orElseThrow(() -> new BugTrackingException("No component found with name: " + componentName));
		return mapComponentToDto(c);
	}

	@Transactional
	public String deleteByName(String componentName) {
		Component c = componentDao.findByComponentName(componentName)
				.orElseThrow(() -> new BugTrackingException("No component found with name: " + componentName));
		componentDao.delete(c);
		return "Component Deleted";
	}

	@Transactional
	public List<String> getAllBugs(String componentName) {
		Component c = componentDao.findByComponentName(componentName)
				.orElseThrow(() -> new BugTrackingException("No component found with name: " + componentName));
		List<Bug> al = bugDao.findByComp(c);
		List<String> ans = new ArrayList<>();
		for (Bug x : al) {
			ans.add(x.getBugDetails());
		}
		return ans;
	}

	@Transactional
	public List<String> getBugUrl(String componentName) {
		Component c = componentDao.findByComponentName(componentName)
				.orElseThrow(() -> new BugTrackingException("No component found with name: " + componentName));
		List<Bug> al = bugDao.findByComp(c);
		List<String> ans = new ArrayList<>();
		for (Bug x : al) {
			ans.add("http://localhost:8080/bugapi/bug/" + x.getBugDetails());
		}
		return ans;
	}

}
