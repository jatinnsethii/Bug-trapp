package com.msit.jatin.main.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msit.jatin.main.dto.ComponentDto;
import com.msit.jatin.main.service.ComponentService;

@RestController
@RequestMapping("bugapi/component")
public class ComponentController {
	private final ComponentService componentService;

	public ComponentController(ComponentService componentService) {
		this.componentService = componentService;
	}

	@PostMapping
	public ResponseEntity<ComponentDto> save(@Valid @RequestBody ComponentDto componentDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(componentService.saveComponent(componentDto));
	}

	@GetMapping
	public ResponseEntity<List<ComponentDto>> getAllComponents() {
		return ResponseEntity.status(HttpStatus.OK).body(componentService.getAll());
	}

	@GetMapping("/{componentName}")
	public ResponseEntity<ComponentDto> getproduct(@PathVariable String componentName) {
		return ResponseEntity.status(HttpStatus.OK).body(componentService.getComponentByName(componentName));
	}

	@DeleteMapping("/{componentName}")
	public ResponseEntity<String> deleteProduct(@PathVariable String componentName) {
		return ResponseEntity.status(HttpStatus.OK).body(componentService.deleteByName(componentName));
	}

	@GetMapping("/{componentName}/bugs")
	public ResponseEntity<List<String>> getBugs(@PathVariable String componentName) {
		return ResponseEntity.status(HttpStatus.OK).body(componentService.getAllBugs(componentName));
	}

	@GetMapping("/{componentName}/bugsUrl")
	public ResponseEntity<List<String>> getBugsUrl(@PathVariable String componentName) {
		return ResponseEntity.status(HttpStatus.OK).body(componentService.getBugUrl(componentName));
	}

}
