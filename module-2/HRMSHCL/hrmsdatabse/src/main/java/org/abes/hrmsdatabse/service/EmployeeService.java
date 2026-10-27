package org.abes.hrmsdatabse.service;

import org.abes.hrmsdatabse.dto.AttendanceEntry;
import org.abes.hrmsdatabse.dto.MarkAttendanceRequest;
import org.abes.hrmsdatabse.model.Attendance;
import org.abes.hrmsdatabse.model.Employee;
import org.abes.hrmsdatabse.repository.AttendanceRepository;
import org.abes.hrmsdatabse.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AttendanceRepository attendanceRepository;

    public EmployeeService(EmployeeRepository employeeRepository, AttendanceRepository attendanceRepository) {
        this.employeeRepository = employeeRepository;
        this.attendanceRepository = attendanceRepository;
    }

    public String createNewEmployee(Employee newEmp){
//        Employee emp1=new Employee(200l,"Ram","Java FSD","developer","djkjk","ghgh");
        employeeRepository.save(newEmp);
           return "Employee is addedd successfully";
    }

    public Employee getEmployeeById(long empid){
        return  employeeRepository.findById(empid).orElse(null);
    }


    @Transactional
    public void deleteEmployee(long empId) {
        Employee employee = findEmployeeForUpdate(empId);
        if (attendanceRepository.existsByEmployee_EmpId(empId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Employee has attendance records");
        }
        employeeRepository.delete(employee);
    }

    @Transactional
    public void markAttendance(MarkAttendanceRequest request) {
        if (request.empId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "empId is required");
        }
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime inTime = request.inTime() == null ? now : request.inTime();
        if (request.outTime() != null && request.outTime().isBefore(inTime)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "outTime must be at or after inTime");
        }

        Employee employee = findEmployeeForUpdate(request.empId());
        LocalDate attendanceDate = now.toLocalDate();
        if (attendanceRepository.existsByEmployee_EmpIdAndAttendancedate(employee.getEmpId(), attendanceDate)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Attendance already marked for today");
        }

        Attendance attendance = new Attendance();
        attendance.setEmployee(employee);
        attendance.setAttendancedate(attendanceDate);
        attendance.setInTime(inTime);
        attendance.setOutTime(request.outTime());
        attendance.setComment(request.comment());
        attendanceRepository.save(attendance);
    }

    @Transactional(readOnly = true)
    public List<AttendanceEntry> todayAttendance() {
        return attendanceRepository.findByAttendancedateAndEmployeeIsNotNullOrderByInTimeDescAttendanceIdDesc(LocalDate.now())
                .stream()
                .map(attendance -> {
                    Employee employee = attendance.getEmployee();
                    return new AttendanceEntry(attendance.getAttendanceId(), employee.getEmpId(),
                            employee.getEmpName(), employee.getDesignation(), attendance.getInTime(),
                            attendance.getOutTime(), attendance.getComment());
                })
                .toList();
    }

    @Transactional
    public void checkOut(long attendanceId) {
        LocalDateTime now = LocalDateTime.now();
        Attendance attendance = attendanceRepository
                .findByAttendanceIdAndAttendancedate(attendanceId, now.toLocalDate())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Attendance record not found today"));
        if (attendance.getOutTime() != null) {
            return;
        }
        if (attendance.getInTime() != null && now.isBefore(attendance.getInTime())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Check-in time is in the future");
        }
        attendance.setOutTime(now);
    }

    private Employee findEmployeeForUpdate(long empId) {
        return employeeRepository.findByEmpId(empId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }
}
