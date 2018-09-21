package com.ddubson.adapters;

import com.ddubson.models.UserProfile;
import lombok.NonNull;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class DownstreamUserProfileServiceAdapter implements UserProfileServiceAdapter {
	private RestTemplate restTemplate;
	private URI userProfileServiceUrl;

	public DownstreamUserProfileServiceAdapter(@NonNull RestTemplate restTemplate,
											   @NonNull String userProfileServiceUrl) {
		this.restTemplate = restTemplate;
		this.userProfileServiceUrl = URI.create(userProfileServiceUrl + "/api/users");
	}

	@Override
	public List<UserProfile> findAll() {
		DownstreamUserProfileResponse[] userInformationResponse =
				this.restTemplate.getForObject(userProfileServiceUrl, DownstreamUserProfileResponse[].class);

		DownstreamUserProfileResponse[] downstreamUserProfiles = Optional.ofNullable(userInformationResponse)
				.orElse(new DownstreamUserProfileResponse[]{});

		return Arrays.stream(downstreamUserProfiles)
				.map(response -> UserProfile
						.builder()
						.firstName(response.getFirstName())
						.lastName(response.getLastName())
						.build())
				.collect(toList());
	}
}
