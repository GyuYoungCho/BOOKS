package com.project.book.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.book.dto.Book;

public interface BookDao extends JpaRepository<Book, Integer>{
	Book getByBookId(int book_id);
	Book getByIsbn(String isbn);
	
	@Query(value="select * from book b " + 
			"where lower(title) like :keyword  "
			, nativeQuery = true)
	Page<Book> findByTitleKeyword(@Param("keyword") String keyword, Pageable pageable);
}
