package no.tidly.modules.organization.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TeamRequest(
        @NotBlank @Size(max = 100) String name,

        @NotNull UUID departmentId,

        UUID leaderId) {
}
