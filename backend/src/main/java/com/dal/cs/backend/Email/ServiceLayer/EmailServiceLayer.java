package com.dal.cs.backend.Email.ServiceLayer;


import com.dal.cs.backend.Email.ClassObject.Email;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * This class implements the IEmailServiceLayer and provides the business logic for the sendEmail()
 * @link :https://www.geeksforgeeks.org/spring-boot-sending-email-via-smtp/
 */
@Service
public class EmailServiceLayer implements IEmailServiceLayer
{
    private static final Logger logger= LogManager.getLogger(EmailServiceLayer.class);

    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;


    public EmailServiceLayer()
    {
        Properties properties = null;

        try (InputStream configFile = new FileInputStream("src/main/resources/application.properties")) {
            properties = new Properties();
            properties.load(configFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

        JavaMailSenderImpl javaMailSender1 = new JavaMailSenderImpl();
        javaMailSender1.setJavaMailProperties(properties);
        javaMailSender1.setHost(properties.getProperty("spring.mail.host"));
        javaMailSender1.setPort(Integer.parseInt((properties.getProperty("spring.mail.port"))));
        javaMailSender1.setUsername(properties.getProperty("spring.mail.username"));
        javaMailSender1.setPassword(properties.getProperty("spring.mail.password"));
//        javaMailSender1.setJavaMailProperties();
        javaMailSender = javaMailSender1;

        sender = "sainimi.sushank@gmail.com";
//        properties.setProperty("spring.mail.host", "smtp.gmail.com");
//        logger.info("SMTP Server: "+ properties.getProperty("spring.mail.host"));
//        properties.setProperty("spring.mail.port", "587");
//        logger.info("SMTP Port: "+ properties.getProperty("spring.mail.port"));
//        properties.setProperty("spring.mail.username", "sainimi.sushank@gmail.com");
//        logger.info("SMTP UserName: "+ properties.getProperty("spring.mail.username"));
//        properties.setProperty("spring.mail.password", "uwanlsworqwfzacr");
    }

    /**
     * This method creates email by setting the to, from, subject and body details of the email.
     * @param email is the real world entity that represents the details of an email
     */
    @Override
    public boolean sendEmail(Email email)
    {
        try
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
            return true;
        }
        catch(Exception e)
        {
          logger.error("Exception in sendEmail() "+e.getMessage());
          return false;
        }
    }
}
