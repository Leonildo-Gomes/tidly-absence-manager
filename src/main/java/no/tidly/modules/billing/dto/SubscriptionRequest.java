package no.tidly.modules.billing.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SubscriptionRequest(
        @JsonProperty("company_id") UUID companyId,
        @JsonProperty("plan_id") UUID planId,
        @JsonProperty("payment_method_id") String paymentMethodId) {
}
