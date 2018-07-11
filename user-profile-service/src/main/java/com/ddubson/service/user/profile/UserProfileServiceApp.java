package com.ddubson.service.user.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class UserProfileServiceApp {
	public static void main(String[] args) {
		SpringApplication.run(UserProfileServiceApp.class);
	}

	@GetMapping("/api/users")
	public List<UserProfile> fetchAll() {
		return null;
	}
}
