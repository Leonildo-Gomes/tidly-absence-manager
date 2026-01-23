package no.tidly.modules.organization.dto;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import no.tidly.modules.organization.domain.enums.Gender;

public record EmployeeRequest(
                @NotBlank @Size(max = 100) String userId,
                @NotBlank @Size(max = 150) String name,
                @NotBlank @Email @Size(max = 150) String email,
                @Size(max = 25) String phone,
                @NotNull Gender gender,
                LocalDate startDate,
                LocalDate endDate,
                UUID companyId,
                UUID teamId,
                Boolean isActive) {
}
