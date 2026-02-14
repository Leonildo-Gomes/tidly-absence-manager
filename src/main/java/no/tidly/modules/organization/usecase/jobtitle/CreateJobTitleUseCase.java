package no.tidly.modules.organization.usecase.jobtitle;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.modules.organization.domain.CompanyEntity;
import no.tidly.modules.organization.domain.JobTitleEntity;
import no.tidly.modules.organization.dto.JobTitleRequest;
import no.tidly.modules.organization.dto.JobTitleResponse;
import no.tidly.modules.organization.mapper.JobTitleMapper;
import no.tidly.modules.organization.repository.CompanyRepository;
import no.tidly.modules.organization.repository.JobTitleRepository;

@Service
@RequiredArgsConstructor
public class CreateJobTitleUseCase {

    private final JobTitleRepository repository;
    private final JobTitleMapper mapper;
    private final CompanyRepository companyRepository;

    public JobTitleResponse execute(JobTitleRequest request) {
        CompanyEntity company = companyRepository.findById(request.companyId())
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + request.companyId()));
        var entity = JobTitleEntity.builder()
                .name(request.name())
                .description(request.description())
                .company(company)
                .isActive(true)
                .build();

        return mapper.toResponse(repository.save(entity));
    }
}
