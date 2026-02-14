package no.tidly.modules.organization.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record JobTitleRequest(
        @NotBlank @Size(max = 100) String name,
        String description,
        @NotNull UUID companyId) {
}
