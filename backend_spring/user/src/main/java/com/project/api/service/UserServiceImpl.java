package com.project.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.api.request.UserRegisterPostReq;
import com.project.entity.User;
import com.project.repository.UserRepository;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public User createUser(UserRegisterPostReq userRegisterInfo) {
		User user = new User();		
		/*
		 * userId 암호화 셋팅
		 */		

		user.setId(userRegisterInfo.getId());
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));
		user.setNickname(userRegisterInfo.getNickname());
		//user.setToken(JwtTokenUtil.getToken(userRegisterInfo.getEmail()));
		
		userRepository.save(user);
		return user;
	}

	@Override
	public User getUserById(String id) {
		// 디비에 유저 정보 조회 (userId 를 통한 조회).
		User user = userRepository.findUserById(id).get();
		return user;
	}
	
	@Override
	public User getUserByNickname(String user_nickname) {
		// 디비에 유저 정보 조회 (userId 를 통한 조회).
		User user = userRepository.findUserByNickname(user_nickname).get();
		return user;
	}

//	@Override
//	public boolean checkEmail(String email) {
//		return userRepository.existsByEmail(email);
//	}
//	
//	private String makeId() {
//		StringBuffer key = new StringBuffer();
//		Random rnd = new Random();
//		key.append("p");
//		for(int i = 0; i < 12; i++) {
//			//0~2 숫자 선택
//			int index = rnd.nextInt(3);
//			switch(index) {
//				case 0:
//					//a ~ z (1+ 97 = 98. => (char)98 = 'b'
//					key.append((char) ((int)(rnd.nextInt(26)) + 97));
//					break;
//				case 1:
//					//대문자 A ~ Z
//					key.append((char) ((int)(rnd.nextInt(26)) + 65));
//					break;
//				case 2:
//					//0 ~ 9
//					key.append((rnd.nextInt(10)));
//					break;
//			}
//		}
//		return key.toString();
//	}

//	@Override
//	public boolean changeToken(String id, String token) {
//		User user = userRepository.findUserById(id).get();
//		//user.setToken(token);
//		userRepository.save(user);
//		return true;
//	}

	@Override
	public boolean checkId(String id) {
		return userRepository.existsById(id);
	}
	
	@Override
	public boolean checkNickname(String nickname) {
		return userRepository.existsByNickname(nickname);
	}

	@Override
	public boolean changeToken(String email, String token) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
//	public boolean checkId(String id) {
//		// TODO Auto-generated method stub
//		return false;
//	}

//	@Override
//	public User getUserByToken(String token) {
//		User user = userRepository.findUserByToken(token).get();
//		return user;
//	}

}
