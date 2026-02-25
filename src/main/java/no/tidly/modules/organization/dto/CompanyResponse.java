package no.tidly.modules.organization.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CompanyResponse(
        UUID id,
        String name,
        String organizationNumber,
        String clerkOrgId,
        Boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

}
