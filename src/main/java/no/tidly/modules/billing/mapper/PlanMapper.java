package no.tidly.modules.billing.mapper;

import org.springframework.stereotype.Component;

import no.tidly.modules.billing.domain.Plan;
import no.tidly.modules.billing.dto.PlanRequest;
import no.tidly.modules.billing.dto.PlanResponse;

@Component
public class PlanMapper {

    public Plan toEntity(PlanRequest request) {
        return Plan.builder()
                .name(request.name())
                .code(request.code())
                .description(request.description())
                .price(request.price())
                .currency(request.currency() != null ? request.currency() : "NOK")
                .interval(request.interval())
                .maxEmployees(request.maxEmployees())
                .features(request.features())
                .isActive(request.isActive() != null ? request.isActive() : true)
                .build();
    }

    public PlanResponse toResponse(Plan plan) {
        return new PlanResponse(
                plan.getId(),
                plan.getName(),
                plan.getCode(),
                plan.getDescription(),
                plan.getPrice(),
                plan.getCurrency(),
                plan.getInterval(),
                plan.getMaxEmployees(),
                plan.getFeatures(),
                plan.getIsActive());
    }
}
