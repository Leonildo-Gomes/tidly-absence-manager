package no.tidly.modules.configuration.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record AbsenceBalanceResponse(
                UUID id,
                UUID employeeId,
                UUID absenceTypeId,
                Integer year,
                BigDecimal totalEntitled,
                BigDecimal usedDays,
                BigDecimal pendingDays,
                BigDecimal remainingDays,
                LocalDateTime createdAt,
                LocalDateTime updatedAt) {
}
