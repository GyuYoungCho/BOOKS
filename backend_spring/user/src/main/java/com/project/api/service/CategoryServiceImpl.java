package com.project.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.api.request.UserRegisterPostReq;
import com.project.entity.Category;
import com.project.entity.User;
import com.project.repository.CategoryRepository;
import com.project.repository.UserCategoryRepository;
import com.project.repository.UserRepository;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public Category columnToCategory(int column_id) {
		Category category = categoryRepository.findCategoryIdByColumnId(column_id).get();
		return category;
	}

	@Override
	public Category categoryToColumn(int category_id) {
		Category category = categoryRepository.findColumnIdByCategoryId(category_id).get();
		return category;
	}

}
