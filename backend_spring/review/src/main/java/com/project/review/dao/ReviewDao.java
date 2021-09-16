package com.project.review.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.review.dto.Book;
import com.project.review.dto.Review;

public interface ReviewDao extends JpaRepository<Review, Integer>{
	List<Review> findByBookId(Book book);
	Review getByReviewId(int review_id);
}
