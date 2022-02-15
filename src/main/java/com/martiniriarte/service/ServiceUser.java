package com.martiniriarte.service;

import java.util.List;

import com.martiniriarte.models.User;

public interface ServiceUser {

	public List<User> getUsers();

	public User getUser(Long id);

	public void deleteUser(User user);

	public void registerUser(User user);
}
