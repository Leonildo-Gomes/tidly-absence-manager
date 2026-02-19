package no.tidly.modules.billing.usecase.invoice;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.modules.billing.domain.Subscription;
import no.tidly.modules.billing.dto.InvoiceResponse;
import no.tidly.modules.billing.mapper.SubscriptionInvoiceMapper;
import no.tidly.modules.billing.repository.SubscriptionInvoiceRepository;
import no.tidly.modules.billing.repository.SubscriptionRepository;

@Service
@RequiredArgsConstructor
public class GetInvoicesUseCase {

    private final SubscriptionInvoiceRepository subscriptionInvoiceRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionInvoiceMapper subscriptionInvoiceMapper;

    @Transactional(readOnly = true)
    public List<InvoiceResponse> execute(UUID companyId) {
        Subscription subscription = subscriptionRepository.findByCompanyId(companyId)
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found for company"));

        return subscriptionInvoiceRepository.findBySubscriptionId(subscription.getId()).stream()
                .map(subscriptionInvoiceMapper::toResponse)
                .toList();
    }
}
