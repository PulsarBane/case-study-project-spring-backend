package com.revature.clp.ecommerce.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.revature.clp.ecommerce.project.entity")
public class CaseStudyProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaseStudyProjectApplication.class, args);
	}

}
