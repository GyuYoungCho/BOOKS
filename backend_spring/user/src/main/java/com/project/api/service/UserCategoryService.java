package com.project.api.service;

import com.project.api.request.CategoryAddPostReq;
import com.project.api.request.UserRegisterPostReq;
import com.project.entity.Category;
import com.project.entity.User;
import com.project.entity.UserCategory;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserCategoryService {
	UserCategory deleteUserCategory(int user_id);
	UserCategory[] getCategoryById(int user_id);
	UserCategory addUserCategory(int user_id, int tag);
	
}
