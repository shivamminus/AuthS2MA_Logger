package com.authorize.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AuthorizationS2MaApplication {
	private static Logger logger = LoggerFactory.getLogger(AuthorizationS2MaApplication.class);

	public static void main(String[] args) {        
		//Increase Timeout of Server so it will not give  504 error
        System.setProperty("server.connection-timeout","120000");
        logger.info("Starting Authorization Server");
		SpringApplication.run(AuthorizationS2MaApplication.class, args);
	}

}
