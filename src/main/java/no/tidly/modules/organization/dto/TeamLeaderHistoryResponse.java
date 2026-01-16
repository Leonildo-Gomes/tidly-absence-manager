package no.tidly.modules.organization.dto;

import java.time.LocalDate;
import java.util.UUID;

public record TeamLeaderHistoryResponse(
        UUID id,
        UUID teamId,
        String teamName,
        UUID leaderId,
        String leaderName,
        LocalDate startDate,
        LocalDate endDate) {
}
