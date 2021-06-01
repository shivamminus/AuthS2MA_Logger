package com.authorize.main.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class AuthenticationRequestTest
{
   @Mock
   private AuthenticationRequest authenticationRequest = new AuthenticationRequest();

   @Test
   @DisplayName("Checking for AuthenticationRequest - if it is loading or not")
   public void authenticationRequestNotNullTest(){
       assertThat(authenticationRequest).isNotNull();
   }

	

	@Test
	public void testUserLoginAllArgs() {
		AuthenticationRequest authenticationRequest = new AuthenticationRequest("adyasha", "dummy");
		assertEquals("adyasha", authenticationRequest.getUserName());
		assertEquals("dummy", authenticationRequest.getPassword());
	}

	@Test
	public void testUserTokenAllArgs() {
		AuthenticationResponse authenticationResponse = new AuthenticationResponse("adyasha", "token");
		assertEquals("adyasha", authenticationResponse.getUserName());
		assertEquals("token", authenticationResponse.getJwtAuthToken());
	}
}
