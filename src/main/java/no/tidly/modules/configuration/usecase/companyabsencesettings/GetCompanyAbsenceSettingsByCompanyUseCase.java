package no.tidly.modules.configuration.usecase.companyabsencesettings;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.modules.configuration.domain.CompanyAbsenceSettingsEntity;
import no.tidly.modules.configuration.dto.CompanyAbsenceSettingsResponse;
import no.tidly.modules.configuration.repository.CompanyAbsenceSettingsRepository;

@Service
@RequiredArgsConstructor
public class GetCompanyAbsenceSettingsByCompanyUseCase {

    private final CompanyAbsenceSettingsRepository repository;

    @Transactional(readOnly = true)
    public List<CompanyAbsenceSettingsResponse> execute(UUID companyId) {
        return repository.findByCompanyId(companyId).stream()
                .map(this::mapToResponse)
                .toList();
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
