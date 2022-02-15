package com.martiniriarte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martiniriarte.models.User;
import com.martiniriarte.service.ServiceUser;

@RestController
public class UserController {
	
	@Autowired
	ServiceUser servUser;

	
	@GetMapping("usuarios/{id}")
	public User getUser(@PathVariable Long id) {
		return servUser.getUser(id);
	}

	@RequestMapping("usuarios")
	public List<User> getUsers() {
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
