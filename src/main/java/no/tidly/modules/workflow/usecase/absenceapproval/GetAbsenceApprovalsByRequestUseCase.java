package no.tidly.modules.workflow.usecase.absenceapproval;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.modules.workflow.dto.AbsenceApprovalResponse;
import no.tidly.modules.workflow.mapper.AbsenceApprovalMapper;
import no.tidly.modules.workflow.repository.AbsenceApprovalRepository;

@Service
@RequiredArgsConstructor
public class GetAbsenceApprovalsByRequestUseCase {

    private final AbsenceApprovalRepository repository;
    private final AbsenceApprovalMapper mapper;

    @Transactional(readOnly = true)
    public List<AbsenceApprovalResponse> execute(UUID absenceRequestId) {
        return this.repository.findByAbsenceRequestId(absenceRequestId).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}
