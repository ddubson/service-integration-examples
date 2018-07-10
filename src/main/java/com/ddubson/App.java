package com.ddubson;

import com.ddubson.adapters.DownstreamUserProfileServiceAdapter;
import com.ddubson.adapters.UserProfileServiceAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Configuration
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class);
	}

	@Bean
	public UserProfileServiceAdapter userProfileServiceAdapter(RestTemplate restTemplate,
															   @Value("service.user-profile.url") String userProfileServiceUrl) {
		return new DownstreamUserProfileServiceAdapter(restTemplate, userProfileServiceUrl);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
