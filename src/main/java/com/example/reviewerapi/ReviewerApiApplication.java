package com.example.reviewerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReviewerApiApplication {

	public static void main(String[] args) {
		Mock.init();
		SpringApplication.run(ReviewerApiApplication.class, args);
	}

}
