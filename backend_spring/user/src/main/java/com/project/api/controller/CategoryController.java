package com.project.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.api.request.CategoryAddPostReq;
import com.project.api.request.UserLoginPostReq;
import com.project.api.request.UserRegisterPostReq;
import com.project.api.response.UserLoginPostRes;
import com.project.api.service.UserService;
import com.project.api.service.UserCategoryService;
import com.project.api.service.CategoryService;
import com.project.common.model.response.BaseResponseBody;
import com.project.common.util.JwtTokenUtil;
import com.project.entity.Category;
import com.project.entity.User;
import com.project.entity.UserCategory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@Api(value = "인증 API", tags = {"category"})
@RestController
@RequestMapping("/api/category")
@Slf4j
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	UserCategoryService userCategoryService;
	
	@GetMapping("/{user_id}")
	@ApiOperation(value = "user_id", notes = "user_id의 tags를 가져온다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
        @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
        @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
        @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
	public ResponseEntity<? extends BaseResponseBody> getCategory(@PathVariable int user_id) {
		UserCategory[] userCategory = userCategoryService.getCategoryById(user_id);
		
		for (int i = 0; i < userCategory.length; i++) {
			int cat_id = userCategory[i].getCategoryId();
			userCategory[i].setCategoryId(categoryService.categoryToColumn(cat_id).getColumnId());
		}
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success", userCategory)); 
	}
	
	@PostMapping("/add")
	@ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.") 
//	@ApiResponses({
//        @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
//        @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
//        @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
//        @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
//    })
	public ResponseEntity<? extends BaseResponseBody> addCategory(
			@RequestBody @ApiParam(value="회원가입 정보", required = true) CategoryAddPostReq tagInfo) {
		
		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.

		System.out.println("*******************");
		int user_id = tagInfo.getUser_id();
		int tag = tagInfo.getTag();
		System.out.println(user_id);
		UserCategory userCategory;
		tag = categoryService.columnToCategory(tag).getCategoryId();
		userCategory = userCategoryService.addUserCategory(user_id, tag);
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	/*
	 * 이메일 중복 확인
	 * 
	 */
	@GetMapping("/delete/{user_id}")
	@ApiOperation(value = "아이디 중복 확인", notes = "회원가입 중 아이디 중복확인") 
//	@ApiResponses({
//        @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
//        @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
//        @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
//        @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
//    })
	public ResponseEntity<? extends BaseResponseBody> deleteCategory(@PathVariable int user_id) {
		UserCategory userCategory = userCategoryService.deleteUserCategory(user_id);
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	
}
