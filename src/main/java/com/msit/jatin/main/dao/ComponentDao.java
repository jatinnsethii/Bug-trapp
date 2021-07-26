package com.msit.jatin.main.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msit.jatin.main.model.Component;
import com.msit.jatin.main.model.Product;

@Repository
public interface ComponentDao extends JpaRepository<Component, Long> {
	Optional<Component> findByComponentName(String componentName);
	List<Component> findByProd(Product p);
}
