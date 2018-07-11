package com.ddubson.adapters;

import org.springframework.web.client.RestTemplate;

public class DownstreamUserProfileServiceContract extends UserProfileServiceContract {
	private String userProfileServiceUrl = "http://localhost:8081";

	@Override
	protected UserProfileServiceAdapter userProfileServiceAdapter() {
		return new DownstreamUserProfileServiceAdapter(new RestTemplate(), userProfileServiceUrl);
	}
}
