package com.project.review.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.review.dto.Book;

public interface BookDao extends JpaRepository<Book, Integer>{
	Book getByBookId(int book_id);
}
