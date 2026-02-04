package no.tidly.modules.workflow.mapper;

import org.springframework.stereotype.Component;

import no.tidly.modules.workflow.domain.AbsenceRequestEntity;
import no.tidly.modules.workflow.dto.AbsenceRequestResponse;

@Component
public class AbsenceRequestMapper {
    public AbsenceRequestResponse toResponse(AbsenceRequestEntity entity) {
        return new AbsenceRequestResponse(
                entity.getId(),
                entity.getEmployee().getId(),
                entity.getEmployee().getName(),
                entity.getAbsenceType().getId(),
                entity.getAbsenceType().getName(),
                entity.getYear(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getTotalDays(),
                entity.getStatus(),
                entity.getComment(),
                entity.getAttachmentPath());
    }
}
