package no.tidly.modules.billing.usecase.subscription;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.modules.billing.dto.SubscriptionResponse;
import no.tidly.modules.billing.mapper.SubscriptionMapper;
import no.tidly.modules.billing.repository.SubscriptionRepository;

@Service
@RequiredArgsConstructor
public class GetActiveSubscriptionUseCase {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    @Transactional(readOnly = true)
    public SubscriptionResponse execute(UUID companyId) {
        return subscriptionRepository.findActiveByCompanyId(companyId)
                .map(subscriptionMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Active subscription not found for company"));
    }
}
