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
public class User extends BaseEntity{
	@Id
    int userId;
	
	@Column(name="id", unique=true)
    String id;

    String nickname;
    
    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;
    
    // 
}
