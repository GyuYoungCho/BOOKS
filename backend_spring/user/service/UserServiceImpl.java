package com.ssafy.api.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.Profile;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.ProfileRepository;
import com.ssafy.db.repository.UserRepository;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProfileRepository profileRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public User createUser(UserRegisterPostReq userRegisterInfo) {
		User user = new User();		
		/*
		 * userId 암호화 셋팅
		 */		
		String userId = makeUserId();

		while(userRepository.existsByUserId(userId)) {
			userId = makeUserId();
		}
		
		user.setUserId(userId);
		user.setEmail(userRegisterInfo.getEmail());
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));
		user.setUsername(userRegisterInfo.getUsername());
		//user.setToken(JwtTokenUtil.getToken(userRegisterInfo.getEmail()));
		
		Profile profile = new Profile();
		profile.setUserId(userId);
		profile.setNickname(userRegisterInfo.getNickname());
		profile.setPhoneNum(userRegisterInfo.getPhoneNum());
		userRepository.save(user);
		profileRepository.save(profile);
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		// 디비에 유저 정보 조회 (userId 를 통한 조회).
		User user = userRepository.findUserByEmail(email).get();
		return user;
	}

	@Override
	public boolean checkEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public boolean changePw(String email, String password) {
		User user = userRepository.findUserByEmail(email).get();
		user.setPassword(passwordEncoder.encode(password));
		userRepository.save(user);
		return true;
	}

	@Override
	public boolean changeStatus(String email, String password) {
		User user = userRepository.findUserByEmail(email).get();
		user.setUserStatus(1);
		userRepository.save(user);
		return true;
	}
	
	private String makeUserId() {
		StringBuffer key = new StringBuffer();
		Random rnd = new Random();
		key.append("p");
		for(int i = 0; i < 12; i++) {
			//0~2 숫자 선택
			int index = rnd.nextInt(3);
			switch(index) {
				case 0:
					//a ~ z (1+ 97 = 98. => (char)98 = 'b'
					key.append((char) ((int)(rnd.nextInt(26)) + 97));
					break;
				case 1:
					//대문자 A ~ Z
					key.append((char) ((int)(rnd.nextInt(26)) + 65));
					break;
				case 2:
					//0 ~ 9
					key.append((rnd.nextInt(10)));
					break;
			}
		}
		return key.toString();
	}

	@Override
	public boolean changeToken(String email, String token) {
		User user = userRepository.findUserByEmail(email).get();
		user.setToken(token);
		userRepository.save(user);
		return true;
	}

	@Override
	public User getUserByToken(String token) {
		User user = userRepository.findUserByToken(token).get();
		return user;
	}

}
