package no.tidly.modules.configuration.usecase.holiday;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.modules.configuration.dto.HolidayResponse;
import no.tidly.modules.configuration.mapper.HolidayMapper;
import no.tidly.modules.configuration.repository.HolidayRepository;

@Service
@RequiredArgsConstructor
public class GetAllHolidaysUseCase {

    private final HolidayRepository repository;
    private final HolidayMapper mapper;

    @Transactional(readOnly = true)
    public List<HolidayResponse> execute(UUID companyId) {
        // Retrieve both company-specific and global (null companyId) holidays
        return repository.findAllByCompanyIdOrGlobal(companyId).stream()
                .map(mapper::toResponse)
                .toList();
    }
}
