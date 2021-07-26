package com.msit.jatin.main.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msit.jatin.main.dto.BugDto;
import com.msit.jatin.main.dto.BugResponse;
import com.msit.jatin.main.service.BugService;

@RestController
@RequestMapping("bugapi/bug")
public class BugController {
	private final BugService bugService;

	public BugController(BugService bugService) {
		this.bugService = bugService;
	}

	@PostMapping
	public ResponseEntity<BugDto> addBug(@Valid @RequestBody BugDto bugDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(bugService.addBug(bugDto));
	}

	@GetMapping
	public ResponseEntity<List<BugResponse>> getAllBugs() {
		return ResponseEntity.status(HttpStatus.OK).body(bugService.getAllBugs());
	}

	@GetMapping("/{bugName}")
	public ResponseEntity<BugResponse> getBug(@PathVariable String bugName) {
		return ResponseEntity.status(HttpStatus.OK).body(bugService.getBug(bugName));
	}

	@DeleteMapping("/{bugName}")
	public ResponseEntity<String> deleteBug(@PathVariable String bugName) {
		return ResponseEntity.status(HttpStatus.OK).body(bugService.deleteBug(bugName));
	}

	@GetMapping("/myBugs")
	public ResponseEntity<List<String>> getMyBugs() {
		return ResponseEntity.status(HttpStatus.OK).body(bugService.getMyBugs());
	}

	@GetMapping("/myBugsUrl")
	public ResponseEntity<List<String>> getMyBugsUrl() {
		return ResponseEntity.status(HttpStatus.OK).body(bugService.getMyBugsUrl());
	}
	@PutMapping("/{bugName}/resolve")
	public ResponseEntity<String> resolveBug(@PathVariable String bugName){
		return ResponseEntity.status(HttpStatus.OK).body(bugService.resolveBug(bugName));
	}
 }
