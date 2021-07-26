package com.msit.jatin.main.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msit.jatin.main.model.VerificationToken;

@Repository
public interface VerificationTokenDao extends JpaRepository<VerificationToken,Long>{

	Optional<VerificationToken> findByToken(String token);

}
