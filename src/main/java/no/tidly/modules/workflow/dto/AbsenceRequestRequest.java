package no.tidly.modules.workflow.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import no.tidly.modules.workflow.domain.enums.AbsenceStatus;

public record AbsenceRequestRequest(
        UUID employeeId,
        UUID absenceTypeId,
        Integer year,
        LocalDate startDate,
        LocalDate endDate,
        BigDecimal totalDays,
        AbsenceStatus status,
        String comment,
        String attachmentPath) {
}
