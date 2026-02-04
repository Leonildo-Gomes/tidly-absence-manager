package no.tidly.modules.workflow.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import no.tidly.modules.workflow.domain.enums.AbsenceStatus;

public record AbsenceRequestResponse(
                UUID id,
                UUID employeeId,
                String employeeName,
                UUID absenceTypeId,
                String absenceTypeName,
                Integer year,
                LocalDate startDate,
                LocalDate endDate,
                BigDecimal totalDays,
                AbsenceStatus status,
                String comment,
                String attachmentPath) {
}
