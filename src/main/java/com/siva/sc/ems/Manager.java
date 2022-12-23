package com.siva.sc.ems;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.siva.sc.ems")
public class Manager {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Manager.class);
        EmployeeServiceImpl service = context.getBean(EmployeeServiceImpl.class);
        service.sendEmail("info@gmail.com");
    }
}
