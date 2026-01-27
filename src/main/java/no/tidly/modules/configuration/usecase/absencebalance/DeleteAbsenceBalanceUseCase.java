package no.tidly.modules.configuration.usecase.absencebalance;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.modules.configuration.dto.repository.AbsenceBalanceRepository;

@Service
@RequiredArgsConstructor
public class DeleteAbsenceBalanceUseCase {

    private final AbsenceBalanceRepository repository;

    @Transactional
    public void execute(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("AbsenceBalance not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
