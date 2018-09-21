package com.ddubson.service.user.profile;

import io.vavr.control.Either;
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
		return parseUserId(userId).fold(
				msg -> ResponseEntity.badRequest().body(msg),
				id -> ok(userProfileRepository.findById(id))
		);
	}

	private Either<String, Long> parseUserId(String userId) {
		if(userId.matches("\\d*")) {
			return Either.right(Long.parseLong(userId));
		} else {
			return Either.left("User ID has to be a number");
		}
	}
}
