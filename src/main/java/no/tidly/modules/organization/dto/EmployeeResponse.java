package no.tidly.modules.organization.dto;

import java.time.LocalDate;
import java.util.UUID;

import no.tidly.modules.organization.domain.enums.Gender;

public record EmployeeResponse(
        UUID id,
        String userId,
        UUID teamId,
        String teamName,
        String phone,
        Gender gender,
        LocalDate startDate,
        LocalDate endDate,
        Boolean isActive) {
}
