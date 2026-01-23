package no.tidly.modules.organization.usecase.company;

import java.util.UUID;

import org.springframework.stereotype.Service;

import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.core.shared.Utils;
import no.tidly.modules.organization.domain.CompanyEntity;
import no.tidly.modules.organization.dto.CompanyRequest;
import no.tidly.modules.organization.repository.CompanyRepository;

@Service
public class UpdateCompanyUseCase {

    private final CompanyRepository companyRepository;

    public UpdateCompanyUseCase(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyEntity execute(UUID id, CompanyRequest request) {
        var company = this.companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        Utils.copyNonNullProperties(request, company);
        return this.companyRepository.save(company);
    }
}
