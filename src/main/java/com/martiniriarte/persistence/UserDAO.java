package com.martiniriarte.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.martiniriarte.models.User;

public interface UserDAO extends JpaRepository<User, Long>{
	
	boolean existsByEmail(String email);
	
	User getByEmail(String email);
	
	@Query("SELECT u.password FROM User u WHERE email = :email")
	String getPasswordByEmail(String email);
	
	@Query("SELECT u.role FROM User u WHERE id = :id")
	boolean isAuthorized(Long id);
}
