package com.ddubson.adapters;

import com.ddubson.models.UserProfile;

import java.util.List;

public interface UserInformationServiceAdapter {
	List<UserProfile> findAll();
}
