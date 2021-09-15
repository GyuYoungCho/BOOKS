package com.project.review.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.review.dao.ReviewDao;

@RestController
@RequestMapping("/review")
@CrossOrigin("*")
public class ReviewController {
	@Autowired
	ReviewDao reviewDao;
	
	
}
