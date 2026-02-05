package no.tidly.modules.configuration.usecase.balancetransaction;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.core.shared.Utils;
import no.tidly.modules.configuration.domain.BalanceTransactionEntity;
import no.tidly.modules.configuration.dto.BalanceTransactionRequest;
import no.tidly.modules.configuration.dto.BalanceTransactionResponse;
import no.tidly.modules.configuration.dto.repository.BalanceTransactionRepository;
import no.tidly.modules.configuration.mapper.BalanceTransactionMapper;

@Service
@RequiredArgsConstructor
public class UpdateBalanceTransactionUseCase {

    private final BalanceTransactionRepository repository;
    private final BalanceTransactionMapper mapper;

    @Transactional
    public BalanceTransactionResponse execute(UUID id, BalanceTransactionRequest request) {
        BalanceTransactionEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BalanceTransaction not found with id: " + id));

        Utils.copyNonNullProperties(request, entity);

        BalanceTransactionEntity updatedEntity = repository.save(entity);
        return mapper.toResponse(updatedEntity);
    }
}
