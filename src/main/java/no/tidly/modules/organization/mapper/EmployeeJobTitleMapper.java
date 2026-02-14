package no.tidly.modules.organization.mapper;

import org.springframework.stereotype.Component;

import no.tidly.modules.organization.domain.EmployeeJobTitle;
import no.tidly.modules.organization.dto.EmployeeJobTitleResponse;

@Component
public class EmployeeJobTitleMapper {

    public EmployeeJobTitleResponse toResponse(EmployeeJobTitle entity) {
        return new EmployeeJobTitleResponse(
                entity.getId(),
                entity.getEmployee().getId(),
                entity.getEmployee().getName(),
                entity.getJobTitle().getId(),
                entity.getJobTitle().getName(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
