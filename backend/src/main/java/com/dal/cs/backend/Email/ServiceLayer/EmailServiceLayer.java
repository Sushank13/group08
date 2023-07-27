package com.dal.cs.backend.Email.ServiceLayer;

import com.dal.cs.backend.Club.ServiceLayer.ClubServiceLayer;
import com.dal.cs.backend.Email.ClassObject.Email;
import com.dal.cs.backend.Email.Exceptions.CustomEmailException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * This class implements the IEmailServiceLayer and provides the business logic for the sendEmail()
 * @link :https://www.geeksforgeeks.org/spring-boot-sending-email-via-smtp/
 */
@Service
public class EmailServiceLayer implements  IEmailServiceLayer
{
    private static final Logger logger= LogManager.getLogger(ClubServiceLayer.class);
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;

    /**
     * This method creates email by setting the to, from, subject and body details of the email.
     * @param email is the real world entity that represents the details of an email
     */
    @Override
    public void sendEmail(Email email)
    {
        logger.info("EmailServiceLayer: entered sendEmail()");
        logger.info("sendEmail(): setting email to, from,subject and body");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setTo(email.getEmailRecipient());
        simpleMailMessage.setSubject(email.getEmailSubject());
        simpleMailMessage.setText(email.getEmailBody());
        logger.info("sendEmail():email to, from,subject and body set successfully");
        logger.info("sendEmail():sending email");
        javaMailSender.send(simpleMailMessage);
    }
}
