package com.siva.sc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ComponentScan(basePackages = "com.siva.sc")
public class Manager {

    public static void main(String[] args) {
//        EmailClient obj = new EmailClient(new AdvancedSpellCheck());
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Manager.class); //new ClassPathXmlApplicationContext("spring-session.xml");
        EmailClient obj = applicationContext.getBean("emailClient", EmailClient.class);
        obj.sendEmail("siva@gmail.com", "Hello World");
    }
}
