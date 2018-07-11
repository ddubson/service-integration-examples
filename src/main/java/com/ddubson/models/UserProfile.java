package com.ddubson.models;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Getter
public class UserProfile {
	private final String firstName;
	private final String lastName;
}
