package com.siva.sc;

import org.springframework.stereotype.Component;

@Component
public class AdvancedSpellCheck implements SpellCheckService {

    public void spellcheck(String body) {
        System.out.println("Advance spell check is done");
    }
}
