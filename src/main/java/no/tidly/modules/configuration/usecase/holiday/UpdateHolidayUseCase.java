package no.tidly.modules.configuration.usecase.holiday;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.core.shared.Utils;
import no.tidly.modules.configuration.domain.HolidayEntity;
import no.tidly.modules.configuration.dto.HolidayResponse;
import no.tidly.modules.configuration.dto.UpdateHolidayRequest;
import no.tidly.modules.configuration.mapper.HolidayMapper;
import no.tidly.modules.configuration.repository.HolidayRepository;

@Service
@RequiredArgsConstructor
public class UpdateHolidayUseCase {

    private final HolidayRepository repository;
    private final HolidayMapper mapper;

    @Transactional
    public HolidayResponse execute(UUID id, UpdateHolidayRequest request) {
        HolidayEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Holiday not found with id: " + id));

        Utils.copyNonNullProperties(request, entity);

        HolidayEntity updatedEntity = repository.save(entity);
        return mapper.toResponse(updatedEntity);
    }
}
