package no.tidly.modules.configuration.usecase.companyabsencesettings;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.modules.configuration.repository.CompanyAbsenceSettingsRepository;

@Service
@RequiredArgsConstructor
public class DeleteCompanyAbsenceSettingsUseCase {

    private final CompanyAbsenceSettingsRepository repository;

    @Transactional
    public void execute(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("CompanyAbsenceSettings not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
