package com.source;
import com.interfaces.InfMail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
/**
 * Created by thien on 8/30/2016.
 */
public class Gmail implements InfMail {

    public static void sendMail(String mailto, String body, String subject) throws MessagingException{


        Properties props = new Properties();
        Properties profile = new Properties();

        FileInputStream in = null;

        try {
            in = new FileInputStream("src/com/properties/gmail.properties");
            props.load(in);
        } catch (FileNotFoundException fileOEx){
            fileOEx.printStackTrace();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }

        try {
            in = new FileInputStream("src/com/properties/gmailUser.properties");
            profile.load(in);
        } catch (FileNotFoundException fileOEx){
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
            //Setting required parameters
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(profile.getProperty("username")));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(mailto));
            message.setSubject(subject);
            message.setText(body);

            // Send message
            Transport.send(message);

            System.out.println("Gmail message was sent successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }
}
