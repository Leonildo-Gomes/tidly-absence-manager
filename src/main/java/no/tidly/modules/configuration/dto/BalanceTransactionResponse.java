package no.tidly.modules.configuration.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import no.tidly.modules.configuration.domain.enums.TransactionType;

public record BalanceTransactionResponse(
                UUID id,
                UUID employeeId,
                String employeeName,
                UUID absenceTypeId,
                String absenceTypeName,
                Integer year,
                BigDecimal amount,
                TransactionType transactionType,
                String description,
                UUID createdBy,
                LocalDateTime createdAt,
                LocalDateTime updatedAt) {
}
