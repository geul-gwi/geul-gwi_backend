package com.posmosalimos.geulgwi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class GeulgwiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeulgwiApplication.class, args);
	}
}