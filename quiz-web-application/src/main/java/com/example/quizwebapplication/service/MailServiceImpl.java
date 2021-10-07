package com.example.quizwebapplication.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(String senderAddress, String recipientAddress, String subject, String text) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(senderAddress);
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(text);
        mailSender.send(email);
    }
}