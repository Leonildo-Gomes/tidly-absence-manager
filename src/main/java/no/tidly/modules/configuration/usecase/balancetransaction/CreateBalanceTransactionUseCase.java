package no.tidly.modules.configuration.usecase.balancetransaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.modules.configuration.domain.BalanceTransactionEntity;
import no.tidly.modules.configuration.dto.BalanceTransactionRequest;
import no.tidly.modules.configuration.dto.BalanceTransactionResponse;
import no.tidly.modules.configuration.dto.repository.BalanceTransactionRepository;

@Service
@RequiredArgsConstructor
public class CreateBalanceTransactionUseCase {

    private final BalanceTransactionRepository repository;

    @Transactional
    public BalanceTransactionResponse execute(BalanceTransactionRequest request) {
        BalanceTransactionEntity entity = BalanceTransactionEntity.builder()
                .employeeId(request.employeeId())
                .absenceTypeId(request.absenceTypeId())
                .year(request.year())
                .amount(request.amount())
                .transactionType(request.transactionType())
                .description(request.description())
                .createdBy(request.createdBy())
                .build();

        BalanceTransactionEntity savedEntity = repository.save(entity);
        return mapToResponse(savedEntity);
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
