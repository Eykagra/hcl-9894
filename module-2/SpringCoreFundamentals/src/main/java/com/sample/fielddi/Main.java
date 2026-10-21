package com.sample.fielddi;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Employee emp = ctx.getBean(Employee.class);
        System.out.println(emp.displayAddress());
    }
}
