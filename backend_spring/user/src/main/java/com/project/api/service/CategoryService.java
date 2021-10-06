package com.project.api.service;

import com.project.api.request.UserRegisterPostReq;
import com.project.entity.Category;
import com.project.entity.User;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface CategoryService {
	Category columnToCategory(int column_id);
	Category categoryToColumn(int category_id);
	
	
}
