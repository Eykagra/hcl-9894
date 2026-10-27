package org.abes.hrmsdatabse.dto;

import java.time.LocalDateTime;

public record MarkAttendanceRequest(
        Long empId,
        LocalDateTime inTime,
        LocalDateTime outTime,
        String comment
) {
}
