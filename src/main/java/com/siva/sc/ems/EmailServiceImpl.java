package com.siva.sc.ems;

import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService{
    @Override
    public void sendEmail(String email, String body) {
        System.out.println(String.format("email is send to %s with body %s", email, body));

    }
}
