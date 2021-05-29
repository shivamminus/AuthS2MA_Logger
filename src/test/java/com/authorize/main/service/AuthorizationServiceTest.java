package com.authorize.main.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.authorize.main.model.AuthenticationRequest;
import com.authorize.main.repository.AuthRequestRepo;



@SpringBootTest
class AuthorizationServiceTest {

	 	@MockBean
	    private AuthRequestRepo userRepo;

	    @Autowired
	    private AuthorizationService userService;
	    
	    

	    @Test
	    void testloadUserByUsername() {
	        AuthenticationRequest user = new AuthenticationRequest("shivam", "123");
	        when(userRepo.findByUserName("shivam")).thenReturn(user);
	        UserDetails userDetails = new User("shivam", "123", new ArrayList<>());
	        assertEquals(userDetails, userService.loadUserByUsername("shivam"));
	    }

	    @Test
	    @DisplayName("Checking for AuthorizationService - if it is loading or not")
	    public void appUserDetailsServiceNotNullTest() {
	        assertThat(userService).isNotNull();
	    }

}
