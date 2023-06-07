package com.onerivet.deskbook.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.onerivet.deskbook.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void emailSend(String toEmail, String Subject, String body) {
		SimpleMailMessage mail=new SimpleMailMessage();
		
		mail.setTo(toEmail);
		mail.setSubject(Subject);
		mail.setText(body);
		javaMailSender.send(mail);
		
		System.out.println("Mail Sent Successfully");
	}
	
	
}
