package com.project.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.book.dao.UserDao;
import com.project.book.dao.UserKeywordDao;
import com.project.book.dto.User;
import com.project.book.dto.UserKeyword;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/keyword")
@CrossOrigin("*")
public class KeywordController {
	
	private static final String SUCCESS = "success";
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserKeywordDao userKeywordDao;
	
	@ApiOperation(value = "user 검색어 목록", response = String.class)
	@GetMapping("/list/{userId}")
	public ResponseEntity<List<UserKeyword>> keywordList(@PathVariable("userId") int userId) throws Exception{
		User user = userDao.getByUserId(userId);
		List<UserKeyword> list = userKeywordDao.findByUserId(user);
		
		if(list!=null)
			return new ResponseEntity<>(list, HttpStatus.OK);
		
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "user 검색어 삭제", response = String.class)
	@DeleteMapping("/")
	public ResponseEntity<String> delete(@RequestParam("keyword") String keyword , @RequestParam("userId") String userId) throws Exception{
		
		User user = userDao.getByUserId(Integer.parseInt(userId));
		UserKeyword user_key = userKeywordDao.getByUserIdAndKeyword(user,keyword);
		userKeywordDao.delete(user_key);
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
	}
}
