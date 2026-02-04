package no.tidly.modules.workflow.mapper;

import org.springframework.stereotype.Component;

import no.tidly.modules.workflow.domain.AbsenceApprovalEntity;
import no.tidly.modules.workflow.dto.AbsenceApprovalResponse;

@Component
public class AbsenceApprovalMapper {

    public AbsenceApprovalResponse toResponse(AbsenceApprovalEntity entity) {
        return new AbsenceApprovalResponse(
                entity.getId(),
                entity.getAbsenceRequest().getId(),
                entity.getApprover().getId(),
                entity.getApprover().getName(),
                entity.getStatus(),
                entity.getNotes(),
                entity.getCreatedAt());
    }
}
