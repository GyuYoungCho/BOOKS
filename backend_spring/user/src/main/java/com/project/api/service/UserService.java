package com.project.api.service;

import com.project.api.request.UserRegisterPostReq;
import com.project.entity.User;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserService {
	User createUser(UserRegisterPostReq userRegisterInfo);
//	User getUserByEmail(String email);
//	User getUserByToken(String token);
	User getUserById(String id);
	boolean checkId(String id);
	boolean changeToken(String email, String token);
	boolean checkNickname(String nickname);
	User getUserByNickname(String user_nickname);
}
