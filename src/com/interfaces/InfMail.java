package com.interfaces;

/**
 * Created by tuan on 31/08/2016.
 */
public interface InfMail {
    static void sendMail( String rec, String body, String subject){}

    static void sendMailwithAttachment (String rec, String body, String subject, String attachment){}
}
