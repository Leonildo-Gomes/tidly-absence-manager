package no.tidly.modules.organization.usecase.company;

import org.springframework.stereotype.Service;

import no.tidly.modules.organization.domain.CompanyEntity;
import no.tidly.modules.organization.dto.CompanyRequest;
import no.tidly.modules.organization.repository.CompanyRepository;

@Service
public class CreateCompanyUseCase {

    private final CompanyRepository companyRepository;

    public CreateCompanyUseCase(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyEntity execute(CompanyRequest request) {

        var company = CompanyEntity.builder()
                .name(request.name())
                .orgNumber(request.organizationNumber())
                .build();
        return this.companyRepository.save(company);
    }
}
