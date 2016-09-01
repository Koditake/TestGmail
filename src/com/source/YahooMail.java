package com.source;

import com.interfaces.InfMail;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.Transport;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


/**
 * Created by tuan on 31/08/2016.
 */
public class YahooMail implements InfMail {
    public static void sendMail(String mailto, String body, String subject) throws MessagingException {

        FileInputStream in = null;

        Properties props = new Properties();
        Properties profile = new Properties();

        try {
            in = new FileInputStream("src/com/properties/yahoomail.properties");
            props.load(in);
        } catch (FileNotFoundException fileOEx){
            fileOEx.printStackTrace();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }

        try {
            in = new FileInputStream("src/com/properties/yahooMailUser.properties");
            profile.load(in);
        } catch (FileNotFoundException fileOEx) {
            fileOEx.printStackTrace();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(profile.getProperty("username"),profile.getProperty("password"));
            }
        });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(profile.getProperty("username")));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailto));

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setText(body);

            // Send message
            Transport.send(message);

            System.out.println("YahooMail message was sent successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
