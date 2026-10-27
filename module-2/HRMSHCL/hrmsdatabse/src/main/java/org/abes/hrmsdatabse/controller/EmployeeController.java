package org.abes.hrmsdatabse.controller;

import org.abes.hrmsdatabse.dto.MarkAttendanceRequest;
import org.abes.hrmsdatabse.dto.AttendanceEntry;
import org.abes.hrmsdatabse.model.Employee;
import org.abes.hrmsdatabse.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/v1/user")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/")
    public String msg(){
        return "<h2>Welcome to Sptinboot App development</h2>";
    }

    @PostMapping("/addemp")
    public String addnewEmployee(@RequestBody Employee emp){
        System.out.println(emp.getEmail());
        return  employeeService.createNewEmployee(emp);
    }


    @GetMapping("findbyid/{empid}")
    public Employee getEmpById(@PathVariable long empid){
        System.out.println("Employee id:"+empid);
        return employeeService.getEmployeeById(empid);
    }

    @DeleteMapping("/deletebyid/{empid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable long empid) {
        employeeService.deleteEmployee(empid);
    }

    @PostMapping("/mark")
    @ResponseStatus(HttpStatus.CREATED)
    public void markAttendance(@RequestBody MarkAttendanceRequest request) {
        employeeService.markAttendance(request);
    }

    @GetMapping("/attendance/today")
    public List<AttendanceEntry> todayAttendance() {
        return employeeService.todayAttendance();
    }

    @PatchMapping("/attendance/{attendanceId}/checkout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void checkOut(@PathVariable long attendanceId) {
        employeeService.checkOut(attendanceId);
    }
}
