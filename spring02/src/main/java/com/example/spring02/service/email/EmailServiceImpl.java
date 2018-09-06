package com.example.spring02.service.email;

import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.spring02.model.email.EmailDTO;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Inject
	JavaMailSender mailSender;
	
	@Override
	public void sendMail(EmailDTO dto) {
		try {
			MimeMessage msg = mailSender.createMimeMessage();
			//받는 사람 이메일 주소
			msg.addRecipient(RecipientType.TO, new InternetAddress(dto.getReceiveMail()));
			msg.addFrom(new InternetAddress[] {
					new InternetAddress(dto.getSenderMail())
			});
			//이메일 제목처리
			msg.setSubject(dto.getSubject(), "utf-8");
			//이메일 본문
			msg.setText(dto.getMessage(), "utf-8");
			//이메일 보내기
			mailSender.send(msg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
