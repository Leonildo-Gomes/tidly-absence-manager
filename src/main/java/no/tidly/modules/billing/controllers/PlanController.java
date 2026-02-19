package no.tidly.modules.billing.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import no.tidly.modules.billing.dto.PlanRequest;
import no.tidly.modules.billing.dto.PlanResponse;
import no.tidly.modules.billing.usecase.plan.CreatePlanUseCase;
import no.tidly.modules.billing.usecase.plan.GetPlansUseCase;

@RestController
@RequestMapping("/api/v1/plans")
@Tag(name = "Billing - Plans", description = "Endpoints for managing subscription plans")
public class PlanController {

    private final GetPlansUseCase getPlansUseCase;
    private final CreatePlanUseCase createPlanUseCase;

    public PlanController(GetPlansUseCase getPlansUseCase, CreatePlanUseCase createPlanUseCase) {
        this.getPlansUseCase = getPlansUseCase;
        this.createPlanUseCase = createPlanUseCase;
    }

    @GetMapping
    @Operation(summary = "Get all active plans")
    public ResponseEntity<List<PlanResponse>> getPlans() {
        return ResponseEntity.ok(getPlansUseCase.execute());
    }

    @PostMapping
    @Operation(summary = "Create a new plan")
    public ResponseEntity<PlanResponse> createPlan(@RequestBody PlanRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createPlanUseCase.execute(request));
    }
}
