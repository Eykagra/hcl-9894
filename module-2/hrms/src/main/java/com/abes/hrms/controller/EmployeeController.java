package com.abes.hrms.controller;

import com.abes.hrms.model.DemoEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private DemoEmployee demoEmployee;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to Springboot Application";
    }

    @GetMapping("/add")
    public int sum() {
        System.out.println("Inside /add controller point");
        int result = demoEmployee.add();
        System.out.println("Result from model=" + result);
        return result;
    }

    @GetMapping("/sqrt")
    public double getSquareRoot() {
        return demoEmployee.squareRoot();
    }
}
