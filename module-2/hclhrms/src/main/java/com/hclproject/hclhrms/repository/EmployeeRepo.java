package com.hclproject.hclhrms.repository;

import com.hclproject.hclhrms.entity.Employee;

import java.util.List;

public interface EmployeeRepo {
    String addemp(Employee emp);

    String addEmpBulk(List<Employee> bulkEmp);

    List<Employee> getEmp();

    Employee getEmpById(long empid);

    String deleteById(long empid);

    String updateById(long empid, Employee updatedEmp);
}
