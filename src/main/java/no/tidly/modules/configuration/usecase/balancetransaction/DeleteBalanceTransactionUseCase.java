package no.tidly.modules.configuration.usecase.balancetransaction;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.modules.configuration.repository.BalanceTransactionRepository;

@Service
@RequiredArgsConstructor
public class DeleteBalanceTransactionUseCase {

    private final BalanceTransactionRepository repository;

    @Transactional
    public void execute(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("BalanceTransaction not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
