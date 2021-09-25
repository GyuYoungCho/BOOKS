package com.project.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.api.request.UserRegisterPostReq;
import com.project.api.response.UserLoginPostRes;
import com.project.api.service.UserService;
import com.project.common.model.response.BaseResponseBody;
import com.project.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Api(value = "인증 API", tags = {"user"})
@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
//	@PutMapping("/login")
//	@ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
//        @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
//        @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
//        @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
//    })
//	public ResponseEntity<UserLoginPostRes> login(@RequestBody @ApiParam(value="로그인 정보", required = true) UserLoginPostReq loginInfo) {
//		String email = loginInfo.getEmail();
//		String password = loginInfo.getPassword();
//		
//		//이메일로 수정
//		User user = userService.getUserByEmail(email);
//		Profile profile = profileService.getProfileByUserId(user.getUserId());
//		/*
//		 * 탈퇴한 회원(user status 가 1일때)인 경우 로그인 에러
//		 */
//		if(user.getUserStatus() == 1)
//			return ResponseEntity.status(401).body(UserLoginPostRes.of(401, "탈퇴한 회원입니다.", null));
//
//		// 로그인 요청한 유저로부터 입력된 패스워드 와 디비에 저장된 유저의 암호화된 패스워드가 같은지 확인.(유효한 패스워드인지 여부 확인)
//		if(passwordEncoder.matches(password, user.getPassword())) {
//			// 유효한 패스워드가 맞는 경우, 로그인 성공으로 응답.(액세스 토큰을 포함하여 응답값 전달)
//			log.info("로그인 성공했습니다");
//			String token = JwtTokenUtil.getToken(email);
//			userService.changeToken(email, token);
//			return ResponseEntity.ok(UserLoginPostRes.of(200, profile.getNickname(), token));
//		}
//		// 유효하지 않는 패스워드인 경우, 로그인 실패로 응답.
//		return ResponseEntity.status(401).body(UserLoginPostRes.of(401, "Invalid Password", null));
//	}
	
	@PostMapping("/signup")
	@ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.") 
	@ApiResponses({
        @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
        @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
        @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
        @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
	public ResponseEntity<? extends BaseResponseBody> register(
			@RequestBody @ApiParam(value="회원가입 정보", required = true) UserRegisterPostReq registerInfo) {
		
		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		User user = userService.createUser(registerInfo);
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	/*
	 * 이메일 중복 확인
	 * 
	 */
	@GetMapping("/checkId/{id}")
	@ApiOperation(value = "아이디 중복 확인", notes = "회원가입 중 아이디 중복확인") 
	@ApiResponses({
        @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
        @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
        @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
        @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
	public ResponseEntity<Boolean> checkId(@PathVariable String id) {
		System.out.println(id);
		return ResponseEntity.ok(userService.checkId(id));
	}
	
	/*
	 * 유저네임 중복 확인
	 * 
	 */
	@GetMapping("/checkNickname/{nickname}")
	@ApiOperation(value = "닉네임 중복 확인", notes = "회원가입 중 닉네임 중복확인") 
	@ApiResponses({
        @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
        @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
        @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
        @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
	public ResponseEntity<Boolean> checkUsername(@PathVariable String nickname) {
		return ResponseEntity.ok(userService.checkNickname(nickname));
	}
	
}
