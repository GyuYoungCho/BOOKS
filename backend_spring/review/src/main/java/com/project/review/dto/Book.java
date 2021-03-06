package com.project.review.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
    private int bookId;
	
	private String title;
	private String isbn;
	private String coverfilepath;
	private int categoryId;
}
