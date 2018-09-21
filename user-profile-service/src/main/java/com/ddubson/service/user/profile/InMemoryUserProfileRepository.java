package com.ddubson.service.user.profile;

import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.Arrays.asList;

@Repository
public class InMemoryUserProfileRepository implements UserProfileRepository {
	private final List<UserProfile> inMemoryUserProfiles;

	public InMemoryUserProfileRepository() {
		this.inMemoryUserProfiles = asList(UserProfile.builder().firstName("John").lastName("Doe").build());
	}

	public List<UserProfile> fetchAll() {
		return null;
	}
}
