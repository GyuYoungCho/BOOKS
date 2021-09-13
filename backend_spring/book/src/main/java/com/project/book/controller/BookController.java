package com.project.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.book.dao.BookDao;
import com.project.book.dto.Book;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/book")
@CrossOrigin("*")
public class BookController {
	
	@Autowired
	BookDao boodao;
	
	@ApiOperation(value = "단어를 포함한 book 검색", response = String.class)
	@GetMapping("/search")
	public Page<Book> getAptDealInfo(@RequestParam("keyword") String keyword ,final Pageable pageable){
		String url = "";
		return null;
	}
}
