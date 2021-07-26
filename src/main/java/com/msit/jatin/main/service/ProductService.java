package com.msit.jatin.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msit.jatin.main.dao.BugDao;
import com.msit.jatin.main.dao.ComponentDao;
import com.msit.jatin.main.dao.ProductDao;
import com.msit.jatin.main.dto.ProductDto;
import com.msit.jatin.main.exceptions.BugTrackingException;
import com.msit.jatin.main.model.Bug;
import com.msit.jatin.main.model.Component;
import com.msit.jatin.main.model.Product;

@Service
public class ProductService {
	private final ProductDao productDao;
	private final AuthService authService;
	private final ComponentDao componentDao;
	private final BugDao bugDao;

	public ProductService(ProductDao productDao, AuthService authService, ComponentDao componentDao, BugDao bugDao) {
		this.productDao = productDao;
		this.bugDao = bugDao;
		this.componentDao = componentDao;
		this.authService = authService;

	}

	public ProductDto save(ProductDto productDto) {
		Product p = productDao.findByProductName(productDto.getName()).orElse(p=null);
		if(p==null) {
			Product product = productDao.save(mapDtoToProduct(productDto));
			productDto.setId(product.getProductId());
		}else {
			throw new IllegalArgumentException("Product with name is already present");
		}
		return productDto;
		}

	private Product mapDtoToProduct(ProductDto productDto) {
		Product p = new Product();
		p.setProductName(productDto.getName());
		;
		p.setProductDesc(productDto.getDesc());
		return p;
	}

	private ProductDto mapProductToDto(Product product) {
		ProductDto x = new ProductDto();
		x.setId(product.getProductId());
		x.setName(product.getProductName());
		x.setDesc(product.getProductDesc());
		return x;
	}

	@Transactional(readOnly = true)
	public List<ProductDto> getAll() {
		List<Product> pl = productDao.findAll();
		List<ProductDto> ans = new ArrayList<>();
		for (Product p : pl) {
			ans.add(mapProductToDto(p));
		}
		return ans;

	}

	@Transactional(readOnly = true)
	public ProductDto getProductByName(String name) {
		Product p = productDao.findByProductName(name)
				.orElseThrow(() -> new NoSuchElementException("No Product found with name: " + name));
		return mapProductToDto(p);
	}

	@Transactional
	public String deleteByName(String productName) {
		productDao.deleteByProductName(productName)
				.orElseThrow(() -> new NoSuchElementException("No Product found with name: " + productName));
		return "Product Deleted";
	}

	@Transactional
	public List<String> findAllComponents(String productName) {
		Product p = productDao.findByProductName(productName)
				.orElseThrow(() -> new NoSuchElementException ("No Product found with name: " + productName));
		List<Component> al = componentDao.findByProd(p);
		List<String> ans = new ArrayList<String>();
		for (Component x : al) {
			ans.add(x.getComponentName());
		}
		return ans;
	}

	@Transactional
	public List<String> findAllComponentsUrl(String productName) {
		Product p = productDao.findByProductName(productName)
				.orElseThrow(() -> new BugTrackingException("No Product found with name: " + productName));
		List<Component> al = componentDao.findByProd(p);
		List<String> ans = new ArrayList<String>();
		for (Component x : al) {
			ans.add("http://localhost:8080/bugapi/component/" + x.getComponentName());
		}
		return ans;
	}

	public List<String> findAllBugs(String productName) {
		Product p = productDao.findByProductName(productName)
				.orElseThrow(() -> new BugTrackingException("No Product found with name: " + productName));
		List<Bug> al = bugDao.findByProd(p);
		List<String> ans = new ArrayList<String>();
		for (Bug x : al) {
			ans.add(x.getBugDetails());
		}
		return ans;
	}

	@Transactional
	public List<String> findAllBugUrl(String productName) {
		Product p = productDao.findByProductName(productName)
				.orElseThrow(() -> new BugTrackingException("No Product found with name: " + productName));
		List<Bug> al = bugDao.findByProd(p);
		List<String> ans = new ArrayList<String>();
		for (Bug x : al) {
			ans.add("http://localhost:8080/bugapi/bug/" + x.getBugDetails());
		}
		return ans;
	}

}
