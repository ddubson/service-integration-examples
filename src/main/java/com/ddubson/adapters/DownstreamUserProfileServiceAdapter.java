package com.ddubson.adapters;

import com.ddubson.models.UserProfile;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class DownstreamUserProfileServiceAdapter implements UserProfileServiceAdapter {
	private RestTemplate restTemplate;
	private String userProfileServiceUrl;

	public DownstreamUserProfileServiceAdapter(RestTemplate restTemplate, String userProfileServiceUrl) {
		this.restTemplate = restTemplate;
		this.userProfileServiceUrl = userProfileServiceUrl;
	}

	@Override
	public List<UserProfile> findAll() {
		ResponseEntity<DownstreamUserProfileResponse[]> userInformationResponse =
				this.restTemplate.exchange(userProfileServiceUrl + "/api/users",
						HttpMethod.GET, null, DownstreamUserProfileResponse[].class);

		List<DownstreamUserProfileResponse> downstreamUserProfileResponse =
				asList(userInformationResponse.getBody());

		return downstreamUserProfileResponse
				.stream()
				.map(response -> UserProfile
						.builder()
						.firstName(response.getFirstName())
						.lastName(response.getLastName())
						.build())
				.collect(Collectors.toList());
	}
}
