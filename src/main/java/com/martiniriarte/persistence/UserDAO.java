package com.martiniriarte.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martiniriarte.models.User;

public interface UserDAO extends JpaRepository<User, Long>{
	
}
