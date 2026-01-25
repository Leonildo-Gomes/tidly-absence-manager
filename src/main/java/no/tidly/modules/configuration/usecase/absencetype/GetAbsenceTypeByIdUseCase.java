package no.tidly.modules.configuration.usecase.absencetype;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.modules.configuration.domain.AbsenceTypeEntity;
import no.tidly.modules.configuration.dto.AbsenceTypeResponse;
import no.tidly.modules.configuration.repository.AbsenceTypeRepository;

@Service
@RequiredArgsConstructor
public class GetAbsenceTypeByIdUseCase {

    private final AbsenceTypeRepository absenceTypeRepository;

    @Transactional(readOnly = true)
    public AbsenceTypeResponse execute(UUID id) {
        AbsenceTypeEntity entity = absenceTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AbsenceType not found with id: " + id));
        return mapToResponse(entity);
    }

    private AbsenceTypeResponse mapToResponse(AbsenceTypeEntity entity) {
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
