package no.tidly.modules.organization.usecase.company;

import java.util.UUID;

import org.springframework.stereotype.Service;

import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.modules.organization.domain.CompanyEntity;
import no.tidly.modules.organization.repository.CompanyRepository;

@Service
public class DeleteCompanyUseCase {

    private final CompanyRepository repository;

    public DeleteCompanyUseCase(CompanyRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID id) {
        CompanyEntity company = this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        company.setIsActive(false);
        
        this.repository.save(company);
    }
}
