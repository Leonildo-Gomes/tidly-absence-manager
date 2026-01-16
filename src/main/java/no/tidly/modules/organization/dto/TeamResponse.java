package no.tidly.modules.organization.dto;

import java.util.UUID;

public record TeamResponse(
        UUID id,
        String name,
        UUID departmentId,
        String departmentName,
        UUID leaderId,
        String leaderName) {
}
