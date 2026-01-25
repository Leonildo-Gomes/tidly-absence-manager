package no.tidly.modules.configuration.usecase.absencetype;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.modules.configuration.domain.AbsenceTypeEntity;
import no.tidly.modules.configuration.dto.AbsenceTypeRequest;
import no.tidly.modules.configuration.dto.AbsenceTypeResponse;
import no.tidly.modules.configuration.repository.AbsenceTypeRepository;

@Service
@RequiredArgsConstructor
public class CreateAbsenceTypeUseCase {

    private final AbsenceTypeRepository absenceTypeRepository;

    @Transactional
    public AbsenceTypeResponse execute(AbsenceTypeRequest request) {
        AbsenceTypeEntity entity = AbsenceTypeEntity.builder()
                .name(request.name())
                .description(request.description())
                .isPaid(request.isPaid())
                .requiresAttachment(request.requiresAttachment())
                .code(request.code())
                .isActive(request.isActive())
                .build();

        AbsenceTypeEntity savedEntity = absenceTypeRepository.save(entity);
        return mapToResponse(savedEntity);
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
