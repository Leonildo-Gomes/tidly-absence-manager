package no.tidly.modules.configuration.usecase.balancetransaction;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.modules.configuration.domain.BalanceTransactionEntity;
import no.tidly.modules.configuration.dto.BalanceTransactionResponse;
import no.tidly.modules.configuration.dto.repository.BalanceTransactionRepository;
import no.tidly.modules.configuration.mapper.BalanceTransactionMapper;

@Service
@RequiredArgsConstructor
public class GetBalanceTransactionByIdUseCase {

    private final BalanceTransactionRepository repository;
    private final BalanceTransactionMapper mapper;

    @Transactional(readOnly = true)
    public BalanceTransactionResponse execute(UUID id) {
        BalanceTransactionEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BalanceTransaction not found with id: " + id));

        return mapper.toResponse(entity);
    }
}
