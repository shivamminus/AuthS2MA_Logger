package com.authorize.main.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class AuthenticationResponseTest {


	   @Mock
	   private AuthenticationResponse authenticationResponse = new AuthenticationResponse();

	   @Test
	   @DisplayName("Checking for authenticationResponse - if it is loading or not")
	   public void authenticationResponseNotNullTest(){
	       assertThat(authenticationResponse).isNotNull();
	   }

		

		@Test
		public void testUserLoginName() {
			AuthenticationResponse authenticationResponse = new AuthenticationResponse("shivam", "dummy");
			assertEquals("shivam", authenticationResponse.getUserName());
			assertEquals("dummy", authenticationResponse.getJwtAuthToken());
		}

		@Test
		public void testUserToken() {
			AuthenticationResponse authenticationResponse = new AuthenticationResponse("shivam", "token");
			assertEquals("shivam", authenticationResponse.getUserName());
			assertEquals("token", authenticationResponse.getJwtAuthToken());
		}
	}
