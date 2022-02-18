package com.martiniriarte.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.martiniriarte.models.User;

public interface ServiceUser extends UserDetailsService {

	public List<User> getUsers();

	public User getUser(Long id);

	public void deleteUser(User user);

	public void registerUser(User user);
	
	public UserDetails loadUserByEmail(String email);
}
