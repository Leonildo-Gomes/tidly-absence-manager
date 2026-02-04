package no.tidly.modules.workflow.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import no.tidly.modules.workflow.domain.enums.AbsenceStatus;

public record AbsenceApprovalResponse(
                UUID id,
                UUID absenceRequestId,
                UUID approverId,
                String approverName,
                AbsenceStatus status,
                String notes,
                LocalDateTime createdAt) {
}
