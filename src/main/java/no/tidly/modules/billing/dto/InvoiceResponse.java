package no.tidly.modules.billing.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import no.tidly.modules.billing.domain.enums.BillingReason;
import no.tidly.modules.billing.domain.enums.InvoiceStatus;

public record InvoiceResponse(
                UUID id,
                @JsonProperty("subscription_id") UUID subscriptionId,
                BigDecimal amount,
                InvoiceStatus status,
                @JsonProperty("billing_reason") BillingReason billingReason,
                @JsonProperty("period_start") LocalDateTime periodStart,
                @JsonProperty("period_end") LocalDateTime periodEnd,
                @JsonProperty("created_at") LocalDateTime createdAt) {
}
