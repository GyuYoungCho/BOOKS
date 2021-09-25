package com.project.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원 정보 조회 API ([GET] /api/profile/{nickname}) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("UserResponse")
public class UserRes{
	@ApiModelProperty(name="nickname")
	String nickname;
	/*
	 * 추가
	 */
	@ApiModelProperty(name="phoneNum")
	String phoneNum;
	@ApiModelProperty(name="aboutMe")
	String aboutMe;
	@ApiModelProperty(name="totalpoint")
	int totalPoint;
	
//	public static UserRes of(Profile profile) {
//		UserRes res = new UserRes();
//		res.setNickname(profile.getNickname());
//		res.setPhoneNum(profile.getPhoneNum());
//		res.setAboutMe(profile.getAbout_me());
//		res.setTotalPoint(profile.getTotalPoint());
//		return res;
//	}
}
