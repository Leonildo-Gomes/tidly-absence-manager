package no.tidly.modules.billing.usecase.plan;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.modules.billing.dto.PlanResponse;
import no.tidly.modules.billing.mapper.PlanMapper;
import no.tidly.modules.billing.repository.PlanRepository;

@Service
@RequiredArgsConstructor
public class GetPlansUseCase {

    private final PlanRepository planRepository;
    private final PlanMapper planMapper;

    @Transactional(readOnly = true)
    public List<PlanResponse> execute() {
        return planRepository.findByIsActiveTrue().stream()
                .map(planMapper::toResponse)
                .toList();
    }
}
