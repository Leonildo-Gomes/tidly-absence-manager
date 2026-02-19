package no.tidly.modules.billing.usecase.subscription;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.modules.billing.domain.Plan;
import no.tidly.modules.billing.domain.Subscription;
import no.tidly.modules.billing.dto.SubscriptionRequest;
import no.tidly.modules.billing.dto.SubscriptionResponse;
import no.tidly.modules.billing.mapper.SubscriptionMapper;
import no.tidly.modules.billing.repository.PlanRepository;
import no.tidly.modules.billing.repository.SubscriptionRepository;

@Service
@RequiredArgsConstructor
public class CreateSubscriptionUseCase {

    private final SubscriptionRepository subscriptionRepository;
    private final PlanRepository planRepository;
    private final SubscriptionMapper subscriptionMapper;

    @Transactional
    public SubscriptionResponse execute(SubscriptionRequest request) {
        // Check if company already has an active subscription
        if (subscriptionRepository.findByCompanyId(request.companyId()).isPresent()) {
            throw new IllegalArgumentException("Company already has a subscription");
        }

        Plan plan = planRepository.findById(request.planId())
                .orElseThrow(() -> new IllegalArgumentException("Plan not found"));

        Subscription subscription = subscriptionMapper.toEntity(request, plan);
        Subscription savedSubscription = subscriptionRepository.save(subscription);
        return subscriptionMapper.toResponse(savedSubscription);
    }
}
