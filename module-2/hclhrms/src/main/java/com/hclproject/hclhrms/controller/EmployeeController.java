package com.hclproject.hclhrms.controller;

import com.hclproject.hclhrms.entity.Employee;
import com.hclproject.hclhrms.service.EmployeeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeServiceImp employeeServiceImp;

    @GetMapping("/")
    public String welcomMsg() {
        return "Welcome To ABES";
    }

    @GetMapping("/addemp")
    public String addNewEmployee() {
        return employeeServiceImp.addEmp();
    }

    @GetMapping("/addemp/bulk")
    public String addBulkEmployees() {
        return employeeServiceImp.addEmpBulk();
    }

    @GetMapping("/getemp")
    public List<Employee> getEmp() {
        return employeeServiceImp.getEmp();
    }

    @GetMapping("/getempbyid")
    public Employee getEmpById(@RequestParam long empid) {
        return employeeServiceImp.getEmpById(empid);
    }
}
