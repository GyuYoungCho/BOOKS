package com.ssafy.api.service;

import com.ssafy.db.entity.Profile;

public interface ProfileService {
	Profile getProfileByNickname(String nickname);
	Profile getProfileByUserId(String userId);
	boolean checkName(String nickname);
	boolean changeProfileInfo(String userId, String nickname, String aboutMe);
}