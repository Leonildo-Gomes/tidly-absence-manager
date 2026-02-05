package no.tidly.modules.configuration.mapper;

import org.springframework.stereotype.Component;

import no.tidly.modules.configuration.domain.AbsenceTypeEntity;
import no.tidly.modules.configuration.dto.AbsenceTypeResponse;

@Component
public class AbsenceTypeMapper {

    public AbsenceTypeResponse toResponse(AbsenceTypeEntity entity) {
        if (entity == null) {
            return null;
        }
        return new AbsenceTypeResponse(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getIsPaid(),
                entity.getRequiresAttachment(),
                entity.getCode(),
                entity.getIsActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
