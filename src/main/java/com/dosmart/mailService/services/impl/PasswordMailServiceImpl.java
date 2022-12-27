package com.dosmart.mailService.services.impl;

import com.dosmart.mailService.dtos.EmailDetails;
import com.dosmart.mailService.services.PasswordMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class PasswordMailServiceImpl implements PasswordMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
        public String sendEmail(EmailDetails emailDetails) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("dosmartstusece@gmail.com");
        mailMessage.setTo(emailDetails.getRecipient());
        mailMessage.setText(emailDetails.getMsgBody());
        mailMessage.setSubject(emailDetails.getSubject());
        try {
            javaMailSender.send(mailMessage);
            System.out.println("Mail Sent Successfully..");
            return "Mail Sent Successfully";
        }
        catch (Exception exception)
        {
            System.out.println(exception.getMessage());
            return exception.getMessage();
        }
    }
}
