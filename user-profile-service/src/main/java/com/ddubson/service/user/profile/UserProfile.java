package com.ddubson.service.user.profile;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Getter
class UserProfile {
	private final Long userId;
	private final String firstName;
	private final String lastName;
}
