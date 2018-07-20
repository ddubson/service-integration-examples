package com.ddubson.adapters;

import com.ddubson.models.UserProfile;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DownstreamUserProfileServiceAdapterTest {
	private DownstreamUserProfileServiceAdapter adapter;
	private String baseUrl = "BASE_URL";
	private URI fetchAllUsersUrl;
	@Mock
	private RestTemplate restTemplate;

	@Before
	public void beforeEach() {
		adapter = new DownstreamUserProfileServiceAdapter(restTemplate, baseUrl);
	}

	@Test
	public void findAll_whenDownstreamServiceIsAvailableAndReturningUserInfo_returnsListOfProfiles() {
		DownstreamUserProfileResponse response1 = new DownstreamUserProfileResponse("Hello", "There");
		DownstreamUserProfileResponse response2 = new DownstreamUserProfileResponse("Welcome", "Home");
		DownstreamUserProfileResponse[] responses = {response1, response2};

		fetchAllUsersUrl = URI.create(baseUrl + "/api/users");
		when(restTemplate.getForObject(fetchAllUsersUrl, DownstreamUserProfileResponse[].class))
				.thenReturn(responses);

		List<UserProfile> userProfiles = adapter.findAll();

		assertEquals(2, userProfiles.size());
		assertThat(userProfiles.get(0).getFirstName(), equalTo("Hello"));
		assertThat(userProfiles.get(0).getLastName(), equalTo("There"));
		assertThat(userProfiles.get(1).getFirstName(), equalTo("Welcome"));
		assertThat(userProfiles.get(1).getLastName(), equalTo("Home"));
	}

	@Test
	public void findAll_whenDownstreamServiceIsReturningANullResult_returnsAnEmptyListOfProfiles() {
		List<UserProfile> userProfiles = adapter.findAll();
		assertEquals(0, userProfiles.size());
	}
}
