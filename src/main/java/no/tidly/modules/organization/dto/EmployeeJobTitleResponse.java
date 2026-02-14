package no.tidly.modules.organization.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record EmployeeJobTitleResponse(
                UUID id,
                UUID employeeId,
                String employeeName,
                UUID jobTitleId,
                String jobTitleName,
                LocalDate startDate,
                LocalDate endDate,
                LocalDateTime createdAt,
                LocalDateTime updatedAt) {
}
