package com.ddubson.adapters;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class DownstreamUserProfileResponse {
	private final String firstName;
	private final String lastName;
}
