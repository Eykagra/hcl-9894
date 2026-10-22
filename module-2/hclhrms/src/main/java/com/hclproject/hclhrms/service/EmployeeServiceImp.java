package com.hclproject.hclhrms.service;

import com.hclproject.hclhrms.entity.Employee;
import com.hclproject.hclhrms.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImp {

    @Autowired
    private EmployeeRepo emprepo;

    public String addEmp() {
        Employee emp1 = new Employee(200, "Rahul", "FSD", "Java", "random@gmail.com");
        return emprepo.addemp(emp1);
    }

    public String addEmpBulk() {
        List<Employee> bulkEmp = List.of(
                new Employee(201, "Priya", "FSD", "Spring Boot", "priya@gmail.com"),
                new Employee(202, "Aman", "Backend Developer", "Java", "aman@gmail.com"),
                new Employee(203, "Neha", "Frontend Developer", "React", "neha@gmail.com"),
                new Employee(204, "Karan", "Tester", "Java", "karan@gmail.com")
        );
        return emprepo.addEmpBulk(bulkEmp);
    }

    public List<Employee> getEmp() {
        return emprepo.getEmp();
    }

    public Employee getEmpById(long empid) {
        return emprepo.getEmpById(empid);
    }
}
