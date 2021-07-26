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

import com.msit.jatin.main.dto.ProductDto;
import com.msit.jatin.main.service.ProductService;

@RestController
@RequestMapping("bugapi/product")
public class ProductController {
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping
	public ResponseEntity<ProductDto> save(@Valid @RequestBody ProductDto productDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productDto));
	}

	@GetMapping
	public ResponseEntity<List<ProductDto>> getAllProducts() {
		return ResponseEntity.status(HttpStatus.OK).body(productService.getAll());
	}

	@GetMapping("/{productName}")
	public ResponseEntity<ProductDto> getproduct(@PathVariable String productName) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.getProductByName(productName));
	}

	@DeleteMapping("/{productName}")
	public ResponseEntity<String> deleteProduct(@PathVariable String productName) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.deleteByName(productName));
	}

	@GetMapping("/{productName}/components")
	public ResponseEntity<List<String>> getAllComponents(@PathVariable String productName) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.findAllComponents(productName));
	}

	@GetMapping("/{productName}/componentUrl")
	public ResponseEntity<List<String>> getAllComponentsUrl(@PathVariable String productName) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.findAllComponentsUrl(productName));
	}

	@GetMapping("/{productName}/bugs")
	public ResponseEntity<List<String>> getAllBugs(@PathVariable String productName) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.findAllBugs(productName));
	}

	@GetMapping("/{productName}/bugUrl")
	public ResponseEntity<List<String>> getAllBugUrl(@PathVariable String productName) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.findAllBugUrl(productName));
	}
}
