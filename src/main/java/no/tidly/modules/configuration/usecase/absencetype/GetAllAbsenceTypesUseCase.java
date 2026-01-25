package no.tidly.modules.configuration.usecase.absencetype;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.modules.configuration.domain.AbsenceTypeEntity;
import no.tidly.modules.configuration.dto.AbsenceTypeResponse;
import no.tidly.modules.configuration.repository.AbsenceTypeRepository;

@Service
@RequiredArgsConstructor
public class GetAllAbsenceTypesUseCase {

    private final AbsenceTypeRepository absenceTypeRepository;

    @Transactional(readOnly = true)
    public List<AbsenceTypeResponse> execute() {
        return absenceTypeRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
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
