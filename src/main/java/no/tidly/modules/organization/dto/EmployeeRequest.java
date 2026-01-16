package no.tidly.modules.organization.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import no.tidly.modules.organization.domain.enums.Gender;

public record EmployeeRequest(
        @NotBlank @Size(max = 100) String userId,

        @NotNull UUID companyId,

        UUID teamId,

        @Size(max = 25) String phone,

        @NotNull Gender gender) {
}
