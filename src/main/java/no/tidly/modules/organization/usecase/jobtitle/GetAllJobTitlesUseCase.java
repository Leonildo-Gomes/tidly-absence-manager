package no.tidly.modules.organization.usecase.jobtitle;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import no.tidly.modules.organization.dto.JobTitleResponse;
import no.tidly.modules.organization.mapper.JobTitleMapper;
import no.tidly.modules.organization.repository.JobTitleRepository;

@Service
@RequiredArgsConstructor
public class GetAllJobTitlesUseCase {

    private final JobTitleRepository repository;
    private final JobTitleMapper mapper;

    public List<JobTitleResponse> execute() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }
}
