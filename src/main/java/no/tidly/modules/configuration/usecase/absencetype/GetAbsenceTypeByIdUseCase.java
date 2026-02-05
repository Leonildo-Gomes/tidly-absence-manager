package no.tidly.modules.configuration.usecase.absencetype;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.modules.configuration.domain.AbsenceTypeEntity;
import no.tidly.modules.configuration.dto.AbsenceTypeResponse;
import no.tidly.modules.configuration.mapper.AbsenceTypeMapper;
import no.tidly.modules.configuration.repository.AbsenceTypeRepository;

@Service
@RequiredArgsConstructor
public class GetAbsenceTypeByIdUseCase {

    private final AbsenceTypeRepository absenceTypeRepository;
    private final AbsenceTypeMapper mapper;

    @Transactional(readOnly = true)
    public AbsenceTypeResponse execute(UUID id) {
        AbsenceTypeEntity entity = absenceTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AbsenceType not found with id: " + id));
        return mapper.toResponse(entity);
    }
}
