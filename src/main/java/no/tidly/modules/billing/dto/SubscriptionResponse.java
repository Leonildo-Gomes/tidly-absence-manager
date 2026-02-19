package no.tidly.modules.billing.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import no.tidly.modules.billing.domain.enums.SubscriptionStatus;

public record SubscriptionResponse(
                UUID id,
                @JsonProperty("company_id") UUID companyId,
                @JsonProperty("plan_id") UUID planId,
                SubscriptionStatus status,
                @JsonProperty("current_period_start") LocalDateTime currentPeriodStart,
                @JsonProperty("current_period_end") LocalDateTime currentPeriodEnd,
                @JsonProperty("cancel_at_period_end") boolean cancelAtPeriodEnd,
                @JsonProperty("payment_method_id") String paymentMethodId) {
}
