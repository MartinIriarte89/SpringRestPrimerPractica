package com.martiniriarte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martiniriarte.models.User;
import com.martiniriarte.persistence.UserDAO;

@Service
public class ServiceUserImpl implements ServiceUser {

	@Autowired
	UserDAO userDAO;

	@Override
	public List<User> getUsers() {
		return userDAO.findAll();
	}

	@Override
	public User getUser(Long id) {
		return userDAO.findById(id).orElse(null);
	}

	@Override
	public void deleteUser(User user) {
		userDAO.delete(user);
	}
}
