package no.tidly.modules.organization.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DepartmentRequest(
        @NotBlank @Size(max = 100) String name,

        UUID parentDepartmentId,

        @NotNull UUID companyId,

        UUID managerId) {
}
