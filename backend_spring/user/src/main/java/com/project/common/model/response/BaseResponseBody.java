package com.project.common.model.response;

import org.springframework.http.HttpStatus;

import com.project.entity.Category;
import com.project.entity.UserCategory;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 서버 요청에대한 기본 응답값(바디) 정의.
 */
@Getter
@Setter
@ApiModel("BaseResponseBody")
public class BaseResponseBody {
	@ApiModelProperty(name="응답 메시지", example = "정상")
	String message = null;
	@ApiModelProperty(name="응답 코드", example = "200")
	Integer statusCode = null;
	String id = null;
	String password = null;
	String nickname = null;
	int user_id;
	UserCategory[] userCategory;
	Category category;
	
	public BaseResponseBody() {}
	
	public BaseResponseBody(Integer statusCode){
		this.statusCode = statusCode;
	}
	
	public BaseResponseBody(Integer statusCode, String message){
		this.statusCode = statusCode;
		this.message = message;
	}
	
	public BaseResponseBody(Integer statusCode, String message, String id){
		this.statusCode = statusCode;
		this.message = message;
		this.id = id;
	}
	
	public static BaseResponseBody of(Integer statusCode, String message) {
		BaseResponseBody body = new BaseResponseBody();
		body.message = message;
		body.statusCode = statusCode;
		return body;
	}
	public static BaseResponseBody of(Integer statusCode, String message, UserCategory[] userCategory) {
		BaseResponseBody body = new BaseResponseBody();
		body.statusCode = statusCode;
		body.userCategory = userCategory;
		return body;
	}
	
	public static BaseResponseBody of(Integer statusCode, String password, String id, int user_id) {
		BaseResponseBody body = new BaseResponseBody();
		body.statusCode = statusCode;
		body.password = password;
		body.id = id;
		body.user_id = user_id;
		return body;
	}
	public static BaseResponseBody of(Integer statusCode, String message, int user_id, String id, String password, String nickname) {
		BaseResponseBody body = new BaseResponseBody();
		body.message = message;
		body.statusCode = statusCode;
		body.id = id;
		body.user_id = user_id;
		body.password = password;
		body.nickname = nickname;
		return body;
	}
}
