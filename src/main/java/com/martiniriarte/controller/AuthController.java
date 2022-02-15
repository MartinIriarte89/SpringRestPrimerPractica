package com.martiniriarte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.martiniriarte.models.User;
import com.martiniriarte.service.ServiceLogin;

@RestController
public class AuthController {

	@Autowired
	ServiceLogin servLogin;
	
	@PostMapping("login")
	public void login(@RequestBody User user) {
		servLogin.authEmailPassword(user);
	}
	
}
