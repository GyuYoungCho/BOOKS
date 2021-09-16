package com.project.book.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDetail {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
    private int bookId;
	
	private String title;
	private String link;
	private String author;
	
	private Date pubDate;
	
	private String description;
	
	private int priceSales;
	private int priceStandard;
	private int mileage;
	
	private String malltype;
	private String coverFilepath;
	
	private int categoryId;
	private String publisher;
	private int itemPage;
}
