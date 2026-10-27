package org.abes.hrmsdatabse.dto;

import java.time.LocalDateTime;

public record AttendanceEntry(
        long attendanceId,
        long empId,
        String empName,
        String designation,
        LocalDateTime inTime,
        LocalDateTime outTime,
        String comment
) {
}
