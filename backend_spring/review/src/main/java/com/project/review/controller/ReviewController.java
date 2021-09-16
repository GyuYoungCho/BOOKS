package com.project.review.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.review.dao.BookDao;
import com.project.review.dao.ReviewDao;
import com.project.review.dao.UserDao;
import com.project.review.dto.Book;
import com.project.review.dto.Review;
import com.project.review.dto.User;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/review")
@CrossOrigin("*")
public class ReviewController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	ReviewDao reviewDao;
	
	@Autowired
	BookDao bookDao;
	
	@Autowired
	UserDao userDao;
	
	@ApiOperation(value = "review 등록", response = String.class)
	@PostMapping("/")
	public ResponseEntity<String> regist(@RequestParam("rank") String rank, @RequestParam("userId") String userId, 
			@RequestParam("content") String content, @RequestParam("bookId") String bookId) throws Exception{
		Book book = bookDao.getByBookId(Integer.parseInt(bookId));
		User user = userDao.getByUserId(Integer.parseInt(userId));
		reviewDao.save(new Review(0, Integer.parseInt(rank), content, new Date(), user , book));
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "review 삭제", response = String.class)
	@DeleteMapping("/{reviewId}")
	public ResponseEntity<String> delete(@PathVariable("reviewId") int reviewId) throws Exception{
		Review review = reviewDao.getByReviewId(reviewId);
		reviewDao.delete(review);
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "review 수정", response = String.class)
	@PutMapping("/")
	public ResponseEntity<String> modify(@RequestParam("rank") String rank,
			@RequestParam("content") String content, @RequestParam("reviewId") String reviewId) throws Exception{
		Review review = reviewDao.getByReviewId(Integer.parseInt(reviewId));
		review.setRank(Integer.parseInt(rank));
		review.setContent(content);
		reviewDao.save(review);
		
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "book의 review 목록", response = String.class)
	@GetMapping("/{bookId}")
	public ResponseEntity<List<Review>> reviewList(@PathVariable("bookId") int bookId) throws Exception{
		Book book = bookDao.getByBookId(bookId);
		
		List<Review> list = reviewDao.findByBookId(book);
		
		if(list.isEmpty())
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}
