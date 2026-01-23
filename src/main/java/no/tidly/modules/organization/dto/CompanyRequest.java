package no.tidly.modules.organization.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CompanyRequest(
                @NotBlank @Size(max = 150) String name,
                @Size(max = 20) String organizationNumber) {
}
