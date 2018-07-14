package com.ddubson;

import com.ddubson.adapters.DownstreamUserProfileServiceAdapter;
import com.ddubson.adapters.UserProfileServiceAdapter;
import org.springframework.web.client.RestTemplate;

public class DownstreamUserProfileServiceContract extends UserProfileServiceContract {
	private String userProfileServiceUrl = "http://localhost:8081";

	@Override
	protected UserProfileServiceAdapter fullyOperationalUserProfileService() {
		return new DownstreamUserProfileServiceAdapter(new RestTemplate(), userProfileServiceUrl);
	}
}
