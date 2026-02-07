package no.tidly.modules.configuration.usecase.balancetransaction;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.modules.configuration.dto.BalanceTransactionResponse;
import no.tidly.modules.configuration.mapper.BalanceTransactionMapper;
import no.tidly.modules.configuration.repository.BalanceTransactionRepository;

@Service
@RequiredArgsConstructor
public class GetBalanceTransactionsByEmployeeUseCase {

    private final BalanceTransactionRepository repository;
    private final BalanceTransactionMapper mapper;

    @Transactional(readOnly = true)
    public List<BalanceTransactionResponse> execute(UUID employeeId) {
        return repository.findByEmployeeId(employeeId).stream()
                .map(mapper::toResponse)
                .toList();
    }
}
