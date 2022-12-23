package com.siva.sc;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class BasicSpellCheck implements SpellCheckService{

    public void spellcheck(String body) {
        System.out.println("basic spell check is done");
    }
}
