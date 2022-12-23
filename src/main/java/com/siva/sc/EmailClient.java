package com.siva.sc;

import java.sql.SQLOutput;

public class EmailClient {

    private SpellCheckService spellCheckService;

    public EmailClient(SpellCheckService spellCheckService){
        this.spellCheckService = spellCheckService;
    }
    public void sendEmail(String email, String body) {
        System.out.println("Email Send method is called");
        spellCheckService.spellcheck(body);
        System.out.println("Email Sent...");

    }
}
