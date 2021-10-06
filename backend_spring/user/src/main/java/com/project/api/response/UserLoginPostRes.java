package com.project.api.response;

import com.project.common.model.response.BaseResponseBody;
import com.project.entity.UserCategory;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 로그인 API ([POST] /api/auth) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("UserLoginPostResponse") 
public class UserLoginPostRes extends BaseResponseBody{
	@ApiModelProperty(name="JWT 인증 토큰", example="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN...")
	String accessToken;
	
	public static UserLoginPostRes of(Integer statusCode, String password, String id, int user_id) {
		UserLoginPostRes res = new UserLoginPostRes();
		res.setStatusCode(statusCode);
		res.setPassword(password);
		res.setId(id);
		res.setUser_id(user_id);
		return res;
	}
	
	public static UserLoginPostRes of(Integer statusCode, String message, UserCategory[] userCategory) {
		UserLoginPostRes res = new UserLoginPostRes();
		res.setStatusCode(statusCode);
		res.setPassword(message);
		res.setUserCategory(userCategory);
		return res;
	}
}
