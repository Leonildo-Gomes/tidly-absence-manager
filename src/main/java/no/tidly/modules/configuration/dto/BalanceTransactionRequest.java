package no.tidly.modules.configuration.dto;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import no.tidly.modules.configuration.domain.enums.TransactionType;

public record BalanceTransactionRequest(
                @NotNull UUID employeeId,
                @NotNull UUID absenceTypeId,
                @NotNull Integer year,
                @NotNull BigDecimal amount,
                @NotNull TransactionType transactionType,
                String description,
                UUID createdBy) {
}
