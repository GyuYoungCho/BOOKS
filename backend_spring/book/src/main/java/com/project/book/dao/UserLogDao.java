package com.project.book.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.book.dto.User;
import com.project.book.dto.UserLog;

public interface UserLogDao extends JpaRepository<UserLog, String> {
	
	List<UserLog> findByUserId(User user);
}
