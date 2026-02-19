package no.tidly.modules.billing.mapper;

import org.springframework.stereotype.Component;

import no.tidly.modules.billing.domain.SubscriptionInvoice;
import no.tidly.modules.billing.dto.InvoiceResponse;

@Component
public class SubscriptionInvoiceMapper {

    public InvoiceResponse toResponse(SubscriptionInvoice invoice) {
        return new InvoiceResponse(
                invoice.getId(),
                invoice.getSubscription().getId(),
                invoice.getAmount(),
                invoice.getStatus(),
                invoice.getBillingReason(),
                invoice.getPeriodStart(),
                invoice.getPeriodEnd(),
                invoice.getCreatedAt());
    }
}
