package com.siva.sc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class EmailClient {

    private SpellCheckService spellCheckService;

    @Autowired
    public EmailClient(SpellCheckService spellCheckService){
        this.spellCheckService = spellCheckService;
    }
    public void sendEmail(String email, String body) {
        System.out.println("Email Send method is called");
        spellCheckService.spellcheck(body);
        System.out.println("Email Sent...");

    }
}
