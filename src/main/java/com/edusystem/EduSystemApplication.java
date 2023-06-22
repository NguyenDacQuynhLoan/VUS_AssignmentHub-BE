package com.edusystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
