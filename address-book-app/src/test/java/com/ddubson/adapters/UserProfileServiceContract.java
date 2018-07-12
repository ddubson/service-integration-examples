package com.ddubson.adapters;

import com.ddubson.models.UserProfile;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public abstract class UserProfileServiceContract {
	@Test
	public void findAll_whenServiceIsMalfunctioningWithANullResponse_shallReturnAnEmptyList() {
		List<UserProfile> userProfiles = userProfileServiceAdapter().findAll();
		assertTrue(userProfiles.isEmpty());
	}

	protected abstract UserProfileServiceAdapter userProfileServiceAdapter();
}
