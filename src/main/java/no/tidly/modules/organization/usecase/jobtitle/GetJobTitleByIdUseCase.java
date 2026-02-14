package no.tidly.modules.organization.usecase.jobtitle;

import java.util.UUID;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import no.tidly.modules.organization.dto.JobTitleResponse;
import no.tidly.modules.organization.mapper.JobTitleMapper;
import no.tidly.modules.organization.repository.JobTitleRepository;

@Service
@RequiredArgsConstructor
public class GetJobTitleByIdUseCase {

    private final JobTitleRepository repository;
    private final JobTitleMapper mapper;

    public JobTitleResponse execute(UUID id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("JobTitle not found with id: " + id));
        return mapper.toResponse(entity);
    }
}
