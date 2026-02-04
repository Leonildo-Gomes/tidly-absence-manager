package no.tidly.modules.workflow.usecase.absencerequest;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.modules.workflow.repository.AbsenceRequestRepository;

@Service
@RequiredArgsConstructor
public class DeleteAbsenceRequestUseCase {

    private final AbsenceRequestRepository repository;

    @Transactional
    public void execute(UUID id) {
        if (!this.repository.existsById(id)) {
            throw new ResourceNotFoundException("AbsenceRequest not found with id: " + id);
        }
        this.repository.deleteById(id);
    }
}
