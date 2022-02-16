package com.martiniriarte.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.martiniriarte.models.User;

public interface UserDAO extends JpaRepository<User, Long>{
	
	boolean existsByEmail(String email);
	
	@Query("SELECT u.password FROM User u WHERE email = :email")
	String getPasswordByEmail(String email);
}
