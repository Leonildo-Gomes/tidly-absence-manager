package no.tidly.modules.configuration.usecase.absencebalance;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.core.shared.Utils;
import no.tidly.modules.configuration.domain.AbsenceBalanceEntity;
import no.tidly.modules.configuration.dto.AbsenceBalanceRequest;
import no.tidly.modules.configuration.dto.AbsenceBalanceResponse;
import no.tidly.modules.configuration.dto.repository.AbsenceBalanceRepository;
import no.tidly.modules.configuration.mapper.AbsenceBalanceMapper;

@Service
@RequiredArgsConstructor
public class UpdateAbsenceBalanceUseCase {

    private final AbsenceBalanceRepository repository;
    private final AbsenceBalanceMapper mapper;

    @Transactional
    public AbsenceBalanceResponse execute(UUID id, AbsenceBalanceRequest request) {
        AbsenceBalanceEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AbsenceBalance not found with id: " + id));

        Utils.copyNonNullProperties(request, entity);

        AbsenceBalanceEntity updatedEntity = repository.save(entity);
        return mapper.toResponse(updatedEntity);
    }
}
