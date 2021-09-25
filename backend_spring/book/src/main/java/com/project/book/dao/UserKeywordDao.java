package com.project.book.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.book.dto.User;
import com.project.book.dto.UserKeyword;

public interface UserKeywordDao extends JpaRepository<UserKeyword, String>{
	List<UserKeyword> findByUserId(User user);
	UserKeyword getByUserIdAndKeyword(User user, String keyword);
}
