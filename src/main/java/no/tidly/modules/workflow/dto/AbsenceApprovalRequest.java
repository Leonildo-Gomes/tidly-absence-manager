package no.tidly.modules.workflow.dto;

import java.util.UUID;

import no.tidly.modules.workflow.domain.enums.AbsenceStatus;

public record AbsenceApprovalRequest(
        UUID absenceRequestId,
        UUID approverId,
        AbsenceStatus status,
        String notes) {
}
