package no.tidly.modules.configuration.dto;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateHolidayRequest(
        UUID companyId,
        LocalDate date,
        String name,
        Boolean isRecurring,
        Boolean isActive) {
}
