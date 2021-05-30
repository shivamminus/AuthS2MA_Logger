package com.authorize.main.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authorize.main.AuthorizationS2MaApplication;
import com.authorize.main.dto.VaildatingDTO;
import com.authorize.main.exception.LoginCredentialNotValid;
import com.authorize.main.exception.LoginException;
import com.authorize.main.model.AuthenticationRequest;
import com.authorize.main.model.AuthenticationResponse;
import com.authorize.main.service.AuthorizationService;
import com.authorize.main.util.JwtUtil;

@RestController
@RequestMapping(value = "/auth")
public class AuthorizationController {
	
	private static Logger logger = LoggerFactory.getLogger(AuthorizationController.class);

	@Autowired
	private AuthorizationService userDetailsService;
	@Autowired
	private JwtUtil jwtTokenUtil;

	private VaildatingDTO vaildatingDTO = new VaildatingDTO();

	/*
	 * This Function will generate authentication token
	 * 
	 * @params AuthenticationRequest, (int String String), { id, userName, password
	 * }
	 * 
	 * @return ResponseEntity, (String String) { userrName, token }
	 */
	@PostMapping("/login")
	public ResponseEntity<Object> createAuthorizationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws LoginException, LoginCredentialNotValid {
//		logger.info(authenticationRequest.toString());

		if (authenticationRequest == null || authenticationRequest.getUserName() == null
				|| authenticationRequest.getPassword() == null) {
			return new ResponseEntity<>("Login Details are not provided as per Requirement", HttpStatus.BAD_REQUEST);
			/*
			 * throw new
			 * LoginCredentialNotValid("Login Details are not provided as per Requirement");
			 */
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());

		logger.info("{} " + userDetails);

		if (userDetails.getPassword().equals(authenticationRequest.getPassword())) {
			logger.info("END - [login(customerLoginCredentials)]");
			return new ResponseEntity<>(new AuthenticationResponse(authenticationRequest.getUserName(),
					jwtTokenUtil.generateToken(userDetails)), HttpStatus.OK);
		} else {
			logger.info("END - [login(customerLoginCredentials)]");
			return new ResponseEntity<>("Invalid Username or Password", HttpStatus.FORBIDDEN);
			/* throw new LoginException("Invalid Username or Password"); */
		}
	}

	/*
	 * This Function will validate authentication token
	 * 
	 * @header authorization token
	 * 
	 * @return ResponseEntity, boolean { validStatus }
	 */
	@GetMapping(path = "/validate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VaildatingDTO> validatingAuthorizationToken(
			@RequestHeader(name = "Authorization") String tokenDup) {
		String token = tokenDup.substring(7);

		try {
			UserDetails user = userDetailsService.loadUserByUsername(jwtTokenUtil.extractUsername(token));

			if (Boolean.TRUE.equals(jwtTokenUtil.validateToken(token, user))) {
				vaildatingDTO.setValidStatus(true);
				return new ResponseEntity<>(vaildatingDTO, HttpStatus.OK);
			} else {
				vaildatingDTO.setValidStatus(false);
				return new ResponseEntity<>(vaildatingDTO, HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			vaildatingDTO.setValidStatus(false);
			return new ResponseEntity<>(vaildatingDTO, HttpStatus.FORBIDDEN);
		}
	}

	/*
	 * This will add multiple records in DB. or Bulk Insertion of records
	 */
	@PostMapping(path = "/insert")
	public ResponseEntity<String> insertUser(@RequestBody List<AuthenticationRequest> insertUserList) {
		return new ResponseEntity<String>(userDetailsService.createUser(insertUserList), HttpStatus.CREATED);

	}

	/*
	 * Test Microservice Connection
	 */
	@GetMapping(path = "/check-connection")
	public ResponseEntity<String> healthCheck() {

		logger.info("Authorization Microservice is Up and Running....");

		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

}