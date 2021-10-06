package com.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.entity.Category;
import com.project.entity.User;
import com.project.entity.UserCategory;

@Repository
public interface UserCategoryRepository extends JpaRepository<UserCategory, Integer>{
	 @Transactional
	Optional<UserCategory> deleteAllByUserId(int user_id);
	UserCategory[] findAllByUserId(int user_id);

}
