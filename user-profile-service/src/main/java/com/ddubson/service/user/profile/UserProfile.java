package com.ddubson.service.user.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
class UserProfile {
	private String firstName;
	private String lastName;
}
