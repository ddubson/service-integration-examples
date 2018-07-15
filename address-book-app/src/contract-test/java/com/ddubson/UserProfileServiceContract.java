package com.ddubson;

import com.ddubson.adapters.UserProfileServiceAdapter;
import com.ddubson.models.UserProfile;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertFalse;

public abstract class UserProfileServiceContract {
	@Test
	@Ignore
	public void findAll_whenServiceIsMalfunctioningWithANullResponse_shallReturnANonEmptyResultSet() {
		List<UserProfile> userProfiles = fullyOperationalUserProfileService().findAll();
		assertFalse(userProfiles.isEmpty());
		assertThat(userProfiles.get(0).getFirstName(), not(isEmptyOrNullString()));
		assertThat(userProfiles.get(1).getFirstName(), not(isEmptyOrNullString()));
	}

	protected abstract UserProfileServiceAdapter fullyOperationalUserProfileService();
}
