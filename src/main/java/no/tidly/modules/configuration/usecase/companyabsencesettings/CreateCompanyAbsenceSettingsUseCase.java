package no.tidly.modules.configuration.usecase.companyabsencesettings;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.modules.configuration.domain.CompanyAbsenceSettingsEntity;
import no.tidly.modules.configuration.dto.CompanyAbsenceSettingsRequest;
import no.tidly.modules.configuration.dto.CompanyAbsenceSettingsResponse;
import no.tidly.modules.configuration.repository.CompanyAbsenceSettingsRepository;

@Service
@RequiredArgsConstructor
public class CreateCompanyAbsenceSettingsUseCase {

    private final CompanyAbsenceSettingsRepository repository;

    @Transactional
    public CompanyAbsenceSettingsResponse execute(CompanyAbsenceSettingsRequest request) {
        CompanyAbsenceSettingsEntity entity = CompanyAbsenceSettingsEntity.builder()
                .companyId(request.companyId())
                .absenceTypeId(request.absenceTypeId())
                .departmentId(request.departmentId())
                .maxDaysPerYear(request.maxDaysPerYear())
                .minNoticeDays(request.minNoticeDays() != null ? request.minNoticeDays() : 0)
                .build();

        CompanyAbsenceSettingsEntity savedEntity = repository.save(entity);
        return mapToResponse(savedEntity);
    }

    private CompanyAbsenceSettingsResponse mapToResponse(CompanyAbsenceSettingsEntity entity) {
        return new CompanyAbsenceSettingsResponse(
                entity.getId(),
                entity.getCompanyId(),
                entity.getAbsenceTypeId(),
                entity.getDepartmentId(),
                entity.getMaxDaysPerYear(),
                entity.getMinNoticeDays(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
