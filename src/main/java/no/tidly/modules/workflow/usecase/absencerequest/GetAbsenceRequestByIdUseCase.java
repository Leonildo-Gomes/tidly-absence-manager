package no.tidly.modules.workflow.usecase.absencerequest;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.modules.workflow.domain.AbsenceRequestEntity;
import no.tidly.modules.workflow.dto.AbsenceRequestResponse;
import no.tidly.modules.workflow.mapper.AbsenceRequestMapper;
import no.tidly.modules.workflow.repository.AbsenceRequestRepository;

@Service
@RequiredArgsConstructor
public class GetAbsenceRequestByIdUseCase {

    private final AbsenceRequestRepository repository;
    private final AbsenceRequestMapper mapper;

    @Transactional(readOnly = true)
    public AbsenceRequestResponse execute(UUID id) {
        AbsenceRequestEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AbsenceRequest not found with id: " + id));
        return mapper.toResponse(entity);
    }
}
