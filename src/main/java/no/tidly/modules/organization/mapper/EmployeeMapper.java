package no.tidly.modules.organization.mapper;

import org.springframework.stereotype.Component;

import no.tidly.modules.organization.domain.EmployeeEntity;
import no.tidly.modules.organization.dto.EmployeeResponse;

@Component
public class EmployeeMapper {

    public EmployeeResponse toResponse(EmployeeEntity entity) {
        return new EmployeeResponse(
                entity.getId(),
                entity.getUserId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getGender(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getIsActive(),
                entity.getCompany() != null ? entity.getCompany().getId() : null,
                entity.getTeam() != null ? entity.getTeam().getId() : null,
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
