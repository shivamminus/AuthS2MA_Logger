package com.authorize.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.authorize.main.model.AuthenticationRequest;
import com.authorize.main.repository.AuthRequestRepo;

@Service
public class AuthorizationService implements UserDetailsService {

	@Autowired
	AuthRequestRepo authRequestRepo;

	/*
	 * This will find the AuthenticationRequest from DB from username
	 * 
	 * @params String username
	 * 
	 * @return AuthenticationRequest authenticationRequestObj
	 */
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		try {
			AuthenticationRequest authenticationRequest = authRequestRepo.findByUserName(userName);

			UserDetails user = new User(authenticationRequest.getUserName(), authenticationRequest.getPassword(),
					new ArrayList<>());

			return user;
		} catch (Exception noValidUserFound) {
			// TODO: handle exception
			return null;
		}

	}

	/*
	 * Test function to insert multiple records
	 */
	public String createUser(List<AuthenticationRequest> insertUserList) {

		for (AuthenticationRequest insertUser : insertUserList) {
			authRequestRepo.save(insertUser);
		}

		return "Succesfully Recorded";

	}

}
