package com.github.chat.utils;

import com.github.chat.controllers.impl.UsersController;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class SendEmail {

//    private static final Logger log = (Logger) LoggerFactory.getLogger(RegForEmail.class);

    public static void regEmail(String emailRecipient, String token) throws IOException {
        Properties props = new Properties();
        props.load(UsersController.class.getClassLoader().getResourceAsStream("email.properties"));
        String email = props.getProperty("email");
        String password = props.getProperty("password");
        try{
            Session session = Session.getDefaultInstance(props,
                    new Authenticator(){
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(email, password);
                        }});
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(email));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailRecipient,false));
            msg.setSubject("Hello new user");
            msg.setText("Follow the link for verification " + token);
            msg.setSentDate(new Date());
            Transport.send(msg);
            System.out.println("Message sent.");
        }catch (MessagingException e){
            System.out.println("Erreur d'envoi, cause: " + e);
        }
    }
}