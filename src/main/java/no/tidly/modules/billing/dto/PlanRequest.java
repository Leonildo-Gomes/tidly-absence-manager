package no.tidly.modules.billing.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import no.tidly.modules.billing.domain.enums.PlanInterval;

public record PlanRequest(
                String name,
                String code,
                String description,
                BigDecimal price,
                String currency,
                PlanInterval interval,
                @JsonProperty("max_employees") Integer maxEmployees,
                String features,
                @JsonProperty("is_active") Boolean isActive) {
}
