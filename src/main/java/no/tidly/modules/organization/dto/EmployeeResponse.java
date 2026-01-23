package no.tidly.modules.organization.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import no.tidly.modules.organization.domain.enums.Gender;

public record EmployeeResponse(
                UUID id,
                String userId,
                String name,
                String email,
                String phone,
                Gender gender,
                LocalDate startDate,
                LocalDate endDate,
                Boolean isActive,
                UUID companyId,
                UUID teamId,
                LocalDateTime createdAt,
                LocalDateTime updatedAt) {
}
