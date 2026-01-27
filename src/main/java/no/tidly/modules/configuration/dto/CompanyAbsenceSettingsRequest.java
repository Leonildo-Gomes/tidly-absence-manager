package no.tidly.modules.configuration.dto;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CompanyAbsenceSettingsRequest(
        @NotNull UUID companyId,
        @NotNull UUID absenceTypeId,
        UUID departmentId,
        @NotNull @DecimalMin("0.0") BigDecimal maxDaysPerYear,
        @Min(0) Integer minNoticeDays) {
}
