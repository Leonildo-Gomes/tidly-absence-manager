package no.tidly.modules.configuration.usecase.absencebalance;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.modules.configuration.dto.AbsenceBalanceResponse;
import no.tidly.modules.configuration.dto.repository.AbsenceBalanceRepository;
import no.tidly.modules.configuration.mapper.AbsenceBalanceMapper;

@Service
@RequiredArgsConstructor
public class GetAbsenceBalancesByEmployeeUseCase {

    private final AbsenceBalanceRepository repository;
    private final AbsenceBalanceMapper mapper;

    @Transactional(readOnly = true)
    public List<AbsenceBalanceResponse> execute(UUID employeeId) {
        return repository.findByEmployeeId(employeeId).stream()
                .map(mapper::toResponse)
                .toList();
    }
}
