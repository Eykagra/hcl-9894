package org.abes.hrmsdatabse.repository;

import jakarta.persistence.LockModeType;
import org.abes.hrmsdatabse.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    boolean existsByEmployee_EmpId(long empId);

    boolean existsByEmployee_EmpIdAndAttendancedate(long empId, LocalDate date);

    List<Attendance> findByAttendancedateAndEmployeeIsNotNullOrderByInTimeDescAttendanceIdDesc(LocalDate date);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Attendance> findByAttendanceIdAndAttendancedate(long attendanceId, LocalDate date);
}
