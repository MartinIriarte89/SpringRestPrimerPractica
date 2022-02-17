package com.martiniriarte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.martiniriarte.models.User;
import com.martiniriarte.service.ServiceLogin;
import com.martiniriarte.service.ServiceValidation;

@RestController
public class AuthController {

	@Autowired
	ServiceLogin servLogin;
	
	@Autowired
	ServiceValidation servValid;
	
	@PostMapping("login")
	public String login(@RequestBody User user) {
		if (servLogin.authEmailPassword(user)) {
			String tokenJwt = servValid.getTokenJWT(user);
			
			return tokenJwt;
		}
		
		return "FAIL";
	}
	
}
