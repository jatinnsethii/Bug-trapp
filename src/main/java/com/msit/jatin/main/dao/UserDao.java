package com.msit.jatin.main.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msit.jatin.main.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);
	
}
