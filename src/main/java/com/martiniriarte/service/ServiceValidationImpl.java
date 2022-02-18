package com.martiniriarte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martiniriarte.models.User;
import com.martiniriarte.persistence.UserDAO;
import com.martiniriarte.util.JWT;

@Service
public class ServiceValidationImpl implements ServiceValidation {

	@Autowired
	JWT jwt;
	@Autowired
	UserDAO userDAO;

	@Override
	public String getTokenJWT(User user) {
		User userBD = userDAO.getByEmail(user.getEmail());

		return jwt.create(String.valueOf(userBD.getId()), userBD.getEmail());
	}

	@Override
	public boolean isAuthorized(String token) {
		String keyUser = jwt.getKey(token);
		Long idUser = Long.parseLong(keyUser);

		return userDAO.isAuthorized(idUser);
	}
}
