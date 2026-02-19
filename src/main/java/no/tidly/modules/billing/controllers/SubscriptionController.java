package no.tidly.modules.billing.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import no.tidly.modules.billing.dto.SubscriptionRequest;
import no.tidly.modules.billing.dto.SubscriptionResponse;
import no.tidly.modules.billing.usecase.subscription.CreateSubscriptionUseCase;
import no.tidly.modules.billing.usecase.subscription.GetCompanySubscriptionUseCase;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
@Tag(name = "Billing - Subscriptions", description = "Endpoints for managing company subscriptions")
public class SubscriptionController {

    private final CreateSubscriptionUseCase createSubscriptionUseCase;
    private final GetCompanySubscriptionUseCase getCompanySubscriptionUseCase;

    @PostMapping
    @Operation(summary = "Create a new subscription for a company")
    public ResponseEntity<SubscriptionResponse> createSubscription(@RequestBody SubscriptionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createSubscriptionUseCase.execute(request));
    }

    @GetMapping("/company/{companyId}")
    @Operation(summary = "Get active subscription for a company")
    public ResponseEntity<SubscriptionResponse> getSubscription(@PathVariable UUID companyId) {
        return ResponseEntity.ok(getCompanySubscriptionUseCase.execute(companyId));
    }
}
