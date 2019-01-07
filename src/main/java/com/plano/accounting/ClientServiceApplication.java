package com.plano.accounting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientServiceApplication {

	private static final Logger logger = LoggerFactory.getLogger(ClientServiceApplication.class);

	public static void main(String[] args) {
		logger.info("run spring boot application.");
		SpringApplication.run(ClientServiceApplication.class, args);
	}

}
