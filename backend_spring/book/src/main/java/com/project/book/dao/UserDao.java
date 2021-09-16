package com.project.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.book.dto.User;

public interface UserDao extends JpaRepository<User,Integer> {
	User getByUserId(int user_id);
}
