package com.project.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.api.request.UserRegisterPostReq;
import com.project.entity.Category;
import com.project.entity.User;
import com.project.entity.UserCategory;
import com.project.repository.CategoryRepository;
import com.project.repository.UserCategoryRepository;
import com.project.repository.UserRepository;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userCategoryService")
public class UserCategoryServiceImpl implements UserCategoryService {
	@Autowired
	UserCategoryRepository userCategoryRepository;

	@Override
	public UserCategory deleteUserCategory(int user_id) {
		userCategoryRepository.deleteAllByUserId(user_id);
		return null;
	}

	@Override
	public UserCategory addUserCategory(int user_id, int tag) {
		
		UserCategory userCategory = new UserCategory();
		userCategory.setUserId(user_id);
		userCategory.setCategoryId(tag);
		System.out.println(tag);
		userCategoryRepository.save(userCategory);
		
		return userCategory;
	}


	
	@Override
	public UserCategory[] getCategoryById(int user_id) {
		return userCategoryRepository.findAllByUserId(user_id);
	}
	
	

}
