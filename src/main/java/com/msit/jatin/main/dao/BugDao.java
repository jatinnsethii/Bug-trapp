package com.msit.jatin.main.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msit.jatin.main.model.Bug;
import com.msit.jatin.main.model.Component;
import com.msit.jatin.main.model.Product;
import com.msit.jatin.main.model.User;

@Repository
public interface BugDao extends JpaRepository<Bug, Long> {
	Optional<Bug> findByBugDetails(String bugDetails);
	List<Bug> findByProd(Product p);
	List<Bug> findByComp(Component c);
	List<Bug> findByUser(User user);
}
