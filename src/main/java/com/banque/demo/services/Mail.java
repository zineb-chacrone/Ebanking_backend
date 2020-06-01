package com.banque.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class Mail implements IMail{


public JavaMailSenderImpl javaMailSender=new JavaMailSenderImpl();

public MailConfig mailConfig=new MailConfig();

    public  void sendEmail(String email, String username, String mdp) {
javaMailSender.setHost("smtp.mailtrap.io");
javaMailSender.setPort(2525);
javaMailSender.setUsername("8c8c761b611f47");
javaMailSender.setPassword("97b47ee0690d27");

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);

        msg.setSubject("Création du Compte  Agent");
        msg.setText("Bonjour votre compte est : \n username :"+username+"\n mot de passe :"+mdp+"\n veuillez chan" +
                "ger votre mot de passe ");
javaMailSender.send(msg);
    }
}
