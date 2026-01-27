package no.tidly.modules.configuration.usecase.balancetransaction;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.modules.configuration.domain.BalanceTransactionEntity;
import no.tidly.modules.configuration.dto.BalanceTransactionResponse;
import no.tidly.modules.configuration.dto.repository.BalanceTransactionRepository;

@Service
@RequiredArgsConstructor
public class GetBalanceTransactionByIdUseCase {

    private final BalanceTransactionRepository repository;

    @Transactional(readOnly = true)
    public BalanceTransactionResponse execute(UUID id) {
        BalanceTransactionEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BalanceTransaction not found with id: " + id));

        return mapToResponse(entity);
    }

    private BalanceTransactionResponse mapToResponse(BalanceTransactionEntity entity) {
        return new BalanceTransactionResponse(
                entity.getId(),
                entity.getEmployeeId(),
                entity.getAbsenceTypeId(),
                entity.getYear(),
                entity.getAmount(),
                entity.getTransactionType(),
                entity.getDescription(),
                entity.getCreatedBy(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
