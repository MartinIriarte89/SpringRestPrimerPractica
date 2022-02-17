package com.martiniriarte.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martiniriarte.models.User;
import com.martiniriarte.service.ServiceUser;
import com.martiniriarte.service.ServiceValidation;

@RestController
public class UserController {
	
	@Autowired
	ServiceUser servUser;
	
	@Autowired
	ServiceValidation servValid;

	
	@GetMapping("usuarios/{id}")
	public User getUser(@PathVariable Long id) {
		return servUser.getUser(id);
	}

	@RequestMapping("usuarios")
	public List<User> getUsers(@RequestHeader(value="Authorization") String token) {
		if(!servValid.isAuthorized(token)) {
			return new ArrayList<>();
		}
		
		return servUser.getUsers();
	}
	
	@DeleteMapping("usuarios/{id}")
	public void deleteUser(@PathVariable Long id) {
		User user = servUser.getUser(id);
		servUser.deleteUser(user);
	}
	
	@PostMapping("usuarios")
	public void registerUser(@RequestBody User user) {
		servUser.registerUser(user);
	}	
}
