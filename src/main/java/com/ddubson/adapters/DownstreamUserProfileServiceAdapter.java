package com.ddubson.adapters;

import com.ddubson.models.UserProfile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class DownstreamUserProfileServiceAdapter implements UserProfileServiceAdapter {
	private RestTemplate restTemplate;
	private String userProfileServiceUrl;

	public DownstreamUserProfileServiceAdapter(RestTemplate restTemplate, String userProfileServiceUrl) {
		this.restTemplate = restTemplate;
		this.userProfileServiceUrl = userProfileServiceUrl;
	}

	@Override
	public List<UserProfile> findAll() {
		ResponseEntity<List<UserInformation>> userInformationResponse =
				this.restTemplate.exchange(userProfileServiceUrl + "/api/users",
						HttpMethod.GET, null, parameterizedType());

		List<UserInformation> userInformation = userInformationResponse.getBody();

		return null;
	}

	private ParameterizedTypeReference<List<UserInformation>> parameterizedType() {
		return new ParameterizedTypeReference<List<UserInformation>>() {
		};
	}
}
