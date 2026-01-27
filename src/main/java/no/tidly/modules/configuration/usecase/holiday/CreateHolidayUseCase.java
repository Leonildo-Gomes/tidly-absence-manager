package no.tidly.modules.configuration.usecase.holiday;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.modules.configuration.domain.HolidayEntity;
import no.tidly.modules.configuration.dto.CreateHolidayRequest;
import no.tidly.modules.configuration.dto.HolidayResponse;
import no.tidly.modules.configuration.mapper.HolidayMapper;
import no.tidly.modules.configuration.repository.HolidayRepository;

@Service
@RequiredArgsConstructor
public class CreateHolidayUseCase {

    private final HolidayRepository repository;
    private final HolidayMapper mapper;

    @Transactional
    public HolidayResponse execute(CreateHolidayRequest request) {
        HolidayEntity entity = mapper.toEntity(request);
        HolidayEntity savedEntity = repository.save(entity);
        return mapper.toResponse(savedEntity);
    }
}
