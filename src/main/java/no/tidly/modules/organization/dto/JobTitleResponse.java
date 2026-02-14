package no.tidly.modules.organization.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record JobTitleResponse(
        UUID id,
        String name,
        String description,
        UUID companyId,
        String companyName,
        boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
