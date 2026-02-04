package no.tidly.modules.organization.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record DepartmentResponse(
                UUID id,
                String name,
                String code,
                UUID companyId,
                String companyName,
                UUID parentDepartmentId,
                String parentDepartmentName,
                LocalDateTime createdAt,
                LocalDateTime updatedAt) {
}
