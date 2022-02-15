package com.martiniriarte.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.martiniriarte.models.User;

@Repository
@Transactional
public class UserDAOImpl {

	@PersistenceContext
	private EntityManager entityManager;

	public List<User> getUsers() {
		String query = "SELECT u FROM User";
		List<User> users = new ArrayList<User>();
		for (Object user : entityManager.createQuery(query).getResultList()) {
			User userCast = (User) user;
			users.add(userCast);
		}
		return users; 	
	}
	
	public void delete(Long id) {
		User user = entityManager.find(User.class, id);
		entityManager.remove(user);
	}
}
