package com.hclproject.hclhrms.repository;

import com.hclproject.hclhrms.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmpRepoImp implements EmployeeRepo {

    List<Employee> empList = new ArrayList<>();

    @Override
    public String addemp(Employee emp) {
        empList.add(emp);
        return "Employee Added Successfully";
    }

    @Override
    public String addEmpBulk(List<Employee> bulkEmp) {
        empList.addAll(bulkEmp);
        return bulkEmp.size() + " Employees Added Successfully";
    }

    @Override
    public List<Employee> getEmp() {
        return empList;
    }

    @Override
    public Employee getEmpById(long empid) {
        return empList.stream()
                .filter(emp -> emp.getEmp_id() == empid)
                .findFirst()
                .orElse(null);
    }

    @Override
    public String deleteById(long empid) {
        return "";
    }

    @Override
    public String updateById(long empid, Employee updatedEmp) {
        return "";
    }
}
