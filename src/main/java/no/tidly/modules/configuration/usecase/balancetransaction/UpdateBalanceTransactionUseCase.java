package no.tidly.modules.configuration.usecase.balancetransaction;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.modules.configuration.domain.BalanceTransactionEntity;
import no.tidly.modules.configuration.dto.BalanceTransactionRequest;
import no.tidly.modules.configuration.dto.BalanceTransactionResponse;
import no.tidly.modules.configuration.dto.repository.BalanceTransactionRepository;

@Service
@RequiredArgsConstructor
public class UpdateBalanceTransactionUseCase {

    private final BalanceTransactionRepository repository;

    @Transactional
    public BalanceTransactionResponse execute(UUID id, BalanceTransactionRequest request) {
        BalanceTransactionEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BalanceTransaction not found with id: " + id));

        entity.setEmployeeId(request.employeeId());
        entity.setAbsenceTypeId(request.absenceTypeId());
        entity.setYear(request.year());
        entity.setAmount(request.amount());
        entity.setTransactionType(request.transactionType());
        entity.setDescription(request.description());
        if (request.createdBy() != null) {
            entity.setCreatedBy(request.createdBy());
        }

        BalanceTransactionEntity updatedEntity = repository.save(entity);
        return mapToResponse(updatedEntity);
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
