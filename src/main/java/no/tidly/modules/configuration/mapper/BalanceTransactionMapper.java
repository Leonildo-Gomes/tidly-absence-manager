package no.tidly.modules.configuration.mapper;

import no.tidly.modules.configuration.domain.BalanceTransactionEntity;
import no.tidly.modules.configuration.dto.BalanceTransactionResponse;

public class BalanceTransactionMapper {

    public BalanceTransactionResponse toResponse(BalanceTransactionEntity entity) {
        return new BalanceTransactionResponse(
                entity.getId(),
                entity.getEmployee().getId(),
                entity.getEmployee().getName(),
                entity.getAbsenceType().getId(),
                entity.getYear(),
                entity.getAmount(),
                entity.getTransactionType(),
                entity.getDescription(),
                entity.getCreatedBy(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }

}
