package com.msit.jatin.main.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msit.jatin.main.model.Product;
@Repository
public interface ProductDao extends JpaRepository<Product, Long>{
	
	Optional<Product> findByProductName(String productName);
	Optional<Void> deleteByProductName(String productName);
}
