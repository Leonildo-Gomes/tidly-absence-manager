package no.tidly.modules.configuration.mapper;

import org.springframework.stereotype.Component;

import no.tidly.modules.configuration.domain.AbsenceBalanceEntity;
import no.tidly.modules.configuration.dto.AbsenceBalanceResponse;

@Component
public class AbsenceBalanceMapper {
    public AbsenceBalanceResponse toResponse(AbsenceBalanceEntity entity) {
        if (entity == null) {
            return null;
        }
        return new AbsenceBalanceResponse(
                entity.getId(),
                entity.getEmployee().getId(),
                entity.getEmployee().getName(),
                entity.getAbsenceType().getId(),
                entity.getAbsenceType().getName(),
                entity.getYear(),
                entity.getTotalEntitled(),
                entity.getUsedDays(),
                entity.getPendingDays(),
                entity.getRemainingDays(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
