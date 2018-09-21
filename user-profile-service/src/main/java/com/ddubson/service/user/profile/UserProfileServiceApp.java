package com.ddubson.service.user.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Configuration
@Import({ UserProfileControllerKt.class })
public class UserProfileServiceApp {
	public static void main(String[] args) {
		SpringApplication.run(UserProfileServiceApp.class);
	}

	@Bean
	public Repository<UserProfile, Long> userProfileRepository() {
		return new InMemoryUserProfileRepository();
	}
}
