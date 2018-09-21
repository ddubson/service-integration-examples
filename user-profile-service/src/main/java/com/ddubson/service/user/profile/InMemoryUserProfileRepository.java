package com.ddubson.service.user.profile;

import java.util.List;

import static java.util.Arrays.asList;

public class InMemoryUserProfileRepository implements Repository<UserProfile, Long> {
	private final List<UserProfile> inMemoryUserProfiles;

	public InMemoryUserProfileRepository() {
		this.inMemoryUserProfiles = asList(
				UserProfile.builder().userId(1L).firstName("John").lastName("Doe").build(),
				UserProfile.builder().userId(2L).firstName("Jane").lastName("Smith").build(),
				UserProfile.builder().userId(3L).firstName("George").lastName("Hamilton").build(),
				UserProfile.builder().userId(4L).firstName("Jennifer").lastName("Tyler").build()
		);
	}

	public List<UserProfile> fetchAll() {
		return inMemoryUserProfiles;
	}

	@Override
	public UserProfile findById(Long id) {
		return inMemoryUserProfiles.stream()
				.filter(profile -> profile.getUserId().equals(id))
				.findFirst()
				.orElseThrow(Exceptions.UserProfileNotFoundException::new);
	}
}