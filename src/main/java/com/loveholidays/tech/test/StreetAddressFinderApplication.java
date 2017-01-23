package com.loveholidays.tech.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

@SpringBootApplication (exclude = SecurityAutoConfiguration.class)
public class StreetAddressFinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreetAddressFinderApplication.class, args);
	}
}
