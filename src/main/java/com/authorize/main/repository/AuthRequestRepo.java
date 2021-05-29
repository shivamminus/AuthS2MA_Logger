package com.authorize.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.authorize.main.model.AuthenticationRequest;


/*
 * JPA extending interface for DB related methods
*/
@Repository
public interface AuthRequestRepo extends JpaRepository<AuthenticationRequest, String> {

	/*
	 * This will find the AuthenticationRequest from DB from username
	 * 
	 * @params String username
	 * 
	 * @return AuthenticationRequest authenticationRequestObj
	 */
	public AuthenticationRequest findByUserName(String username);
}