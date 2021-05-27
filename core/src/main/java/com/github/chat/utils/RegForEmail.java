package com.github.chat.utils;

import com.github.chat.BackendRun;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class RegForEmail {

    private static final Logger log = (Logger) LoggerFactory.getLogger(RegForEmail.class);

    Properties properties = new Properties();

    String email = properties.getProperty("email");
    String password = properties.getProperty("password");
    Session session = Session.getDefaultInstance(properties);

    public void regEmail(String emailRecipient, String token) throws IOException {
        try {
            properties.load(BackendRun.class.getClassLoader().getResourceAsStream("email.properties"));
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(emailRecipient)
            );
            message.setSubject("Hey");
            message.setText("Confirm your email " + token);

            Transport.send(message);

        } catch (MessagingException | NoClassDefFoundError e) {
            log.warning("Enter {}: " + e.getMessage());
        }
    }
}