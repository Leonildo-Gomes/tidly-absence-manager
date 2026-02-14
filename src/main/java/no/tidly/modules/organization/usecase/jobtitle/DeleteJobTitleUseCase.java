package no.tidly.modules.organization.usecase.jobtitle;

import java.util.UUID;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import no.tidly.modules.organization.repository.JobTitleRepository;

@Service
@RequiredArgsConstructor
public class DeleteJobTitleUseCase {

    private final JobTitleRepository repository;

    public void execute(UUID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("JobTitle not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
