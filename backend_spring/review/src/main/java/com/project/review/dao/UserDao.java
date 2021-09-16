package com.project.review.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.review.dto.User;

public interface UserDao extends JpaRepository<User,Integer> {
	User getByUserId(int user_id);
}
