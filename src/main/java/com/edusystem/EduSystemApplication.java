package com.edusystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * ignore spring security
 * exclude = {SecurityAutoConfiguration.class}
 */
@SpringBootApplication()
public class EduSystemApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(EduSystemApplication.class, args);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
