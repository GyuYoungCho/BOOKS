package com.project.review.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class Review {
	private int rank;
	private String content;
	private Date reg_time;
	
//	@ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "userId")
//    private User userId;
	
//	@ManyToOne(fetch = FetchType.EAGER)
//  @JoinColumn(name = "bookId")
//  private Book bookId;
}
