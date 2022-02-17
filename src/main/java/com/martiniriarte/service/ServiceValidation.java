package com.martiniriarte.service;

import com.martiniriarte.models.User;

public interface ServiceValidation {
	
	public String getTokenJWT(User user);
	
	public boolean isAuthorized(String token);

}
