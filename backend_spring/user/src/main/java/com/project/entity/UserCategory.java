package com.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * User DB
 */
@Entity
@Getter
@Setter
public class UserCategory extends BaseEntity{
	@Id
    int userCategoryId;
	
	@Column(name="userId", unique=true)
    int userId;
	int categoryId;

}
