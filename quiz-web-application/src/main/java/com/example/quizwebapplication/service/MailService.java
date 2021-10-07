package com.example.quizwebapplication.service;

public interface MailService {
    void sendEmail(String senderAddress, String recipientAddress, String subject, String text);
}
