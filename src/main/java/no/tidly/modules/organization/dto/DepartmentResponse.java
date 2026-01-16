package no.tidly.modules.organization.dto;

import java.util.UUID;

public record DepartmentResponse(
        UUID id,
        String name,
        UUID parentDepartmentId,
        String parentDepartmentName,
        UUID managerId,
        String managerName) {
}
