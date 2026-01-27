package no.tidly.modules.configuration.usecase.absencebalance;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.modules.configuration.domain.AbsenceBalanceEntity;
import no.tidly.modules.configuration.dto.AbsenceBalanceRequest;
import no.tidly.modules.configuration.dto.AbsenceBalanceResponse;
import no.tidly.modules.configuration.dto.repository.AbsenceBalanceRepository;

@Service
@RequiredArgsConstructor
public class CreateAbsenceBalanceUseCase {

    private final AbsenceBalanceRepository repository;

    @Transactional
    public AbsenceBalanceResponse execute(AbsenceBalanceRequest request) {
        // Here we might check for duplicates using employee+year+type if we want strict
        // business validation before DB constraint hit

        AbsenceBalanceEntity entity = AbsenceBalanceEntity.builder()
                .employeeId(request.employeeId())
                .absenceTypeId(request.absenceTypeId())
                .year(request.year())
                .totalEntitled(request.totalEntitled())
                .usedDays(request.usedDays() != null ? request.usedDays() : java.math.BigDecimal.ZERO)
                .pendingDays(request.pendingDays() != null ? request.pendingDays() : java.math.BigDecimal.ZERO)
                .build();

        AbsenceBalanceEntity savedEntity = repository.save(entity);
        return mapToResponse(savedEntity);
    }

    private AbsenceBalanceResponse mapToResponse(AbsenceBalanceEntity entity) {
        return new AbsenceBalanceResponse(
                entity.getId(),
                entity.getEmployeeId(),
                entity.getAbsenceTypeId(),
                entity.getYear(),
                entity.getTotalEntitled(),
                entity.getUsedDays(),
                entity.getPendingDays(),
                entity.getRemainingDays(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
