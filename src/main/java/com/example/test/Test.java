package com.example.test;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Test {

    public static void main(String[] args) {
//
        double a = 6.58;
        ;
        double b = 2;

        int c = (int) ((int) a / b);
    }
    public static void salam() {
        try {
            String from = "asifkazim@yandex.com";
            String to = "asifkazimov98@gmail.com";

            Properties properties = System.getProperties();
            properties.setProperty("mail.smtp.host", "smtp.yandex.com");
            properties.setProperty("mail.smtp.port", "465");
            properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.setProperty("mail.smtp.socketFactory.port", "465");
            properties.setProperty("mail.smtp.auth", "true");

            Session session = Session.getInstance(properties,new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from,"Menbilirem10#");
                }
            });

            session.setDebug(true);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Test Mail from Java Program");
            message.setText("Some Message");
            Transport.send(message);
            System.out.println("Email Sent successfully....");
        } catch (Exception mex){ mex.printStackTrace(); }
    }
}
