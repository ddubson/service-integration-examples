package com.ddubson.service.user.profile;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserProfileController {
	private final InMemoryUserProfileRepository userProfileRepository;

	@GetMapping("/api/users")
	public List<UserProfile> fetchAll() {
		return userProfileRepository.fetchAll();
	}
}
