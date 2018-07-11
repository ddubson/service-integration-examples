package com.ddubson.adapters;

import com.ddubson.models.UserProfile;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DownstreamUserProfileServiceAdapter implements UserProfileServiceAdapter {
	private RestTemplate restTemplate;
	private String userProfileServiceUrl;

	public DownstreamUserProfileServiceAdapter(RestTemplate restTemplate,
											   String userProfileServiceUrl) {
		this.restTemplate = restTemplate;
		this.userProfileServiceUrl = userProfileServiceUrl;
	}

	@Override
	public List<UserProfile> findAll() {
		ResponseEntity<DownstreamUserProfileResponse[]> userInformationResponse =
				this.restTemplate.exchange(userProfileServiceUrl + "/api/users",
						HttpMethod.GET, null, DownstreamUserProfileResponse[].class);

		Optional<DownstreamUserProfileResponse[]> downstreamUserProfiles = Optional.ofNullable(
				userInformationResponse.getBody());

		return Arrays
				.stream(downstreamUserProfiles.orElse(new DownstreamUserProfileResponse[]{}))
				.map(response -> UserProfile
						.builder()
						.firstName(response.getFirstName())
						.lastName(response.getLastName())
						.build())
				.collect(Collectors.toList());
	}
}
