package com.ssafy.api.service;

import java.util.Random;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailServiceImpl implements EmailService {
	@Autowired
	JavaMailSender emailSender;
	
	public static final String ePw = createKey();

	@Override
	public MimeMessage createMessage(String to) throws Exception {
		System.out.println("보내는 대상 : " + to);
		System.out.println("인증번호 : " + ePw);
		MimeMessage message = emailSender.createMimeMessage();	
		message.addRecipients(RecipientType.TO, to);
		message.setSubject("[우리끼리 예능] 인증번호가 도착했습니다.");
		String msg = "";
		
		msg += "<div style = 'margin:100px;'>";
		msg += "<h1>안녕하세요 <bold>우리끼리 예능</bold>입니다!</h1>";
		msg += "<br>";
		msg += "<p>아래 코드를 입력해주세요!</p>";
		msg += "<br>";
		msg += "<p>감사합니다!</p>";
		msg += "<br>";
		msg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
		msg += "<h3 style='color:blue;'>비밀번호 수정확인 코드입니다.</h3>";
		msg += "<div style='font-size:130%'>";
		msg += "CODE : <strong>";
		msg += ePw+"</strong><div><br/> ";
		msg += "</div>";
		 
		message.setText(msg, "utf-8", "html");
		message.setFrom(new InternetAddress("geipro7236@gmail.com", "우리끼리예능"));
		
		return message;
	}

	public static String createKey() {
		StringBuffer key = new StringBuffer();
		Random rnd = new Random();
		//인증코드 8자리
		for(int i = 0; i < 8; i++) {
			//0~2 까지 랜덤
			int index = rnd.nextInt(3);
			switch(index) {
				case 0:
					//a ~ z (1+ 97 = 98. => (char)98 = 'b'
					key.append((char) ((int)(rnd.nextInt(26)) + 97));
					break;
				case 1:
					//위와 비슷하게 A ~ Z
					key.append((char) ((int)(rnd.nextInt(26)) + 65));
					break;
				case 2:
					//0 ~ 9
					key.append((rnd.nextInt(10)));
					break;
			}
		}
		return key.toString();
	}

	@Override
	public void sendSimpleMessage(String to) throws Exception {
		MimeMessage message = createMessage(to);
		try {
			emailSender.send(message);
		}catch(MailException e){
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
	}

}
