package com.martiniriarte.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.martiniriarte.models.Role;
import com.martiniriarte.models.User;
import com.martiniriarte.persistence.UserDAO;
import com.martiniriarte.util.Encrypt;

@Service("userDetailsService")
public class ServiceUserImpl implements ServiceUser{

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

	@Override
	public void registerUser(User user) {
		user.setPassword(Encrypt.encryptPassword(user.getPassword()));
		userDAO.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		// Los roles deben ser pasados al "User" envueltos en capa de interface
		// GrantedAuthority
		var roles = new ArrayList<GrantedAuthority>();

		for (Role rol : user.getRoles()) {
			// SimpleGrantedAuthority implementa la interfaz GrantedAuthority
			// Y por eso la usamos para instanciar
			roles.add(new SimpleGrantedAuthority(rol.getName()));
		}

		// Objeto user de Spring
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), roles);
	}
	
	@Override
	public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException{
		return loadUserByUsername(email);
	}
}
