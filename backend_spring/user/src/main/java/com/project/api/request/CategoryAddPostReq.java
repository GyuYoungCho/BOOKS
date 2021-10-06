package com.project.api.request;

import com.project.entity.Category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 회원가입 API ([POST] /api/auth/join) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("CategoryAddPostRequest")
public class CategoryAddPostReq {
	
	@ApiModelProperty(name="유저 primary ID", example="1") 
	int user_id;
	
	@ApiModelProperty(name="유저 tag", example="tag")
	int tag;
	
}
