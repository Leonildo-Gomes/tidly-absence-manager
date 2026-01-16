package no.tidly.modules.organization.dto;

import java.time.LocalDate;
import java.util.UUID;

public record DepartmentManagerHistoryResponse(
        UUID id,
        UUID departmentId,
        String departmentName,
        UUID managerId,
        String managerName,
        LocalDate startDate,
        LocalDate endDate) {
}
