package com.techjs.contactmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContactMvcApplication {

	public static boolean isAuthenticated = false;

	public static void main(String[] args) {
		SpringApplication.run(ContactMvcApplication.class, args);
	}

}
