package no.tidly.modules.configuration.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record AbsenceTypeResponse(
        UUID id,
        String name,
        String description,
        Boolean isPaid,
        Boolean requiresAttachment,
        String code,
        Boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
