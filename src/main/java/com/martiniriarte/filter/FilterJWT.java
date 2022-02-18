package com.martiniriarte.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.martiniriarte.service.ServiceUser;
import com.martiniriarte.util.JWT;

@Component
@Order(1)
public class FilterJWT extends OncePerRequestFilter{

	@Autowired
	JWT jwt;
	
	@Autowired
	ServiceUser servUser;
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = getToken(req);
			
		if(token != null && jwt.validateToken(token)) {
				String userEmail = jwt.getValue(token);
				UserDetails user = servUser.loadUserByEmail(userEmail);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
			
		filterChain.doFilter(req, resp);
	}
	
	private String getToken(HttpServletRequest req) {
		String header = req.getHeader("Authorization");
		
		if(header != null && header.startsWith("Bearer")) {
			header.replace("Bearer", "");
		}
		
		return header;
	}

}
