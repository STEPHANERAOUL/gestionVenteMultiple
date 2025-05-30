package com.commerce.config.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
//import org.springframework.mail.MailSendException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private  final JavaMailSender javaMailSender;


    public  void  sendVerificationOtpEmail(String userEmail,String otp, String subject,

                                           String text) throws MessagingException {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                    mimeMessage,"UTF-8");
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text,true);
            mimeMessageHelper.setTo(userEmail);
            javaMailSender.send(mimeMessage);

        }
        catch (MailException | MessagingException e){

            System.out.println("eroooooooooooooooor"+e);
         //  throw new MailSendException("failed to send email");




        }

    }





}
