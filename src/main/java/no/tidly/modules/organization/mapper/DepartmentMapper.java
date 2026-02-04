package no.tidly.modules.organization.mapper;

import no.tidly.modules.organization.domain.DepartmentEntity;
import no.tidly.modules.organization.dto.DepartmentResponse;

public class DepartmentMapper {
    public DepartmentResponse toResponse(DepartmentEntity entity) {
        return new DepartmentResponse(
                entity.getId(),
                entity.getName(),
                entity.getCode(),
                entity.getCompany().getId(),
                entity.getCompany().getName(),
                entity.getParentDepartment() != null ? entity.getParentDepartment().getId() : null,
                entity.getParentDepartment() != null ? entity.getParentDepartment().getName() : null,
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }

}
