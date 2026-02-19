package no.tidly.modules.billing.usecase.subscription;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.modules.billing.dto.SubscriptionResponse;
import no.tidly.modules.billing.mapper.SubscriptionMapper;
import no.tidly.modules.billing.repository.SubscriptionRepository;

@Service
@RequiredArgsConstructor
public class GetCompanySubscriptionUseCase {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    @Transactional(readOnly = true)
    public SubscriptionResponse execute(UUID companyId) {
        return subscriptionRepository.findByCompanyId(companyId)
                .map(subscriptionMapper::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found for company"));
    }
}
