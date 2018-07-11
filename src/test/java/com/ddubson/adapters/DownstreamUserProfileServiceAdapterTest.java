package com.ddubson.adapters;

import com.ddubson.models.UserProfile;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DownstreamUserProfileServiceAdapterTest {
	private DownstreamUserProfileServiceAdapter adapter;
	private String baseUrl = "BASE_URL";
	private RestTemplate restTemplate;

	@Before
	public void beforeEach() {
		restTemplate = mock(RestTemplate.class);
		adapter = new DownstreamUserProfileServiceAdapter(restTemplate, baseUrl);
	}

	@Test
	public void findAll_whenDownstreamServiceIsAvailableAndReturningUserInfo_returnsListOfProfiles() {
		DownstreamUserProfileResponse response1 = new DownstreamUserProfileResponse("Hello", "There");
		DownstreamUserProfileResponse response2 = new DownstreamUserProfileResponse("Welcome", "Home");
		DownstreamUserProfileResponse[] responses = {response1, response2};
		ResponseEntity<DownstreamUserProfileResponse[]> responseEntity =
				new ResponseEntity<>(responses, HttpStatus.OK);

		when(restTemplate.exchange(baseUrl + "/api/users", HttpMethod.GET, null,
				DownstreamUserProfileResponse[].class)).thenReturn(responseEntity);

		List<UserProfile> userProfiles = adapter.findAll();

		assertEquals(2, userProfiles.size());
		assertThat(userProfiles.get(0).getFirstName(), equalTo("Hello"));
		assertThat(userProfiles.get(0).getLastName(), equalTo("There"));
		assertThat(userProfiles.get(1).getFirstName(), equalTo("Welcome"));
		assertThat(userProfiles.get(1).getLastName(), equalTo("Home"));

	}
}
