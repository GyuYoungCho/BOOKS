package com.ssafy.api.service;

import javax.mail.internet.MimeMessage;

public interface EmailService {
	MimeMessage createMessage(String to) throws Exception;
	void sendSimpleMessage(String to) throws Exception;
}
