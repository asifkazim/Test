package com.example.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private EmailCnf emailCnf;

    @PostMapping
    public void sendFeedBack(@RequestBody Feedback feedback,
                             BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()){
            throw new Exception("Feedback is not Valid");
        }
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailCnf.getHost());
        mailSender.setPort(this.emailCnf.getPort());
        mailSender.setUsername(this.emailCnf.getUsername());
        mailSender.setPassword(this.emailCnf.getPassword());

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(feedback.getEmail());
        mailMessage.setTo("asifkazimkapital@gmail.com");
        mailMessage.setSubject("New feedback from" + feedback.getName());
        mailMessage.setText(feedback.getFeedback());

        mailSender.send(mailMessage);
    }
}
