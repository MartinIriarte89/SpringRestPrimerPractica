package com.martiniriarte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martiniriarte.models.User;
import com.martiniriarte.persistence.UserDAO;

@Service
public class ServiceLoginImpl implements ServiceLogin {

	@Autowired
	UserDAO userDAO;
	
	@Override
	public void authEmailPassword(User user) {
		System.out.println(userDAO.existsByEmailAndPassword(user.getEmail(), user.getPassword()));
		
	}

}
