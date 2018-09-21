package com.ddubson.service.user.profile;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
public class UserProfileController {
	private final InMemoryUserProfileRepository userProfileRepository;

	@GetMapping("/api/users")
	public List<UserProfile> fetchAll() {
		return userProfileRepository.fetchAll();
	}

	@GetMapping("/api/users/{userId}")
	public ResponseEntity findUserById(@PathVariable("userId") String userId) {
		return ok(userProfileRepository.findById(Long.parseLong(userId)));
	}
}
