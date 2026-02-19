package no.tidly.modules.billing.usecase.plan;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.modules.billing.domain.Plan;
import no.tidly.modules.billing.dto.PlanRequest;
import no.tidly.modules.billing.dto.PlanResponse;
import no.tidly.modules.billing.mapper.PlanMapper;
import no.tidly.modules.billing.repository.PlanRepository;

@Service
@RequiredArgsConstructor
public class CreatePlanUseCase {

    private final PlanRepository planRepository;
    private final PlanMapper planMapper;

    @Transactional
    public PlanResponse execute(PlanRequest request) {
        if (planRepository.findByCode(request.code()).isPresent()) {
            throw new IllegalArgumentException("Plan with code " + request.code() + " already exists");
        }
        Plan plan = planMapper.toEntity(request);
        Plan savedPlan = planRepository.save(plan);
        return planMapper.toResponse(savedPlan);
    }
}
