package com.project.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.book.dto.Book;

public interface BookDao extends JpaRepository<Book, Integer>{

}
