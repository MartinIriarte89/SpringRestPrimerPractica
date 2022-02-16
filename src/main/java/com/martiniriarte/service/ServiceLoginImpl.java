package com.martiniriarte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martiniriarte.models.User;
import com.martiniriarte.persistence.UserDAO;
import com.martiniriarte.util.Encrypt;

@Service
public class ServiceLoginImpl implements ServiceLogin {

	@Autowired
	UserDAO userDAO;

	@Override
	public boolean authEmailPassword(User user) {
		boolean authCorret = false;

		if (userDAO.existsByEmail(user.getEmail())) {
			authCorret = Encrypt.checkPassword(userDAO.getPasswordByEmail(user.getEmail()), user.getPassword());
		}
		return authCorret;
	}
}
