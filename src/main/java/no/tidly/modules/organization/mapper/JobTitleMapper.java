package no.tidly.modules.organization.mapper;

import org.springframework.stereotype.Component;

import no.tidly.modules.organization.domain.JobTitleEntity;
import no.tidly.modules.organization.dto.JobTitleResponse;

@Component
public class JobTitleMapper {

    public JobTitleResponse toResponse(JobTitleEntity entity) {
        return new JobTitleResponse(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCompany().getId(),
                entity.getCompany().getName(),
                entity.getIsActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
