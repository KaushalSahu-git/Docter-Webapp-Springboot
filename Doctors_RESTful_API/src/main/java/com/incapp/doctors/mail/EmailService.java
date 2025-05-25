package com.incapp.doctors.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    

    public void sendResetLink(String recipientEmail, String resetLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(EmailConstants.ADMIN_EMAIL);
        message.setTo(recipientEmail);                 
        message.setSubject("Password Reset Request");
        message.setText("Click the following link to reset your password: \n\n" + resetLink);

        mailSender.send(message);
    }
}
