package com.edusystem;

//import com.edusystem.configuration.BeforeGenerateAppComponent;
import com.edusystem.services.UserServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication()
//@ComponentScan(basePackageClasses = BeforeGenerateAppComponent.class)
public class AssignmentHubApplication {
	public static void main(String[] args) {
		try {
			SpringApplication.run(AssignmentHubApplication.class, args);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
