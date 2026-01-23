package no.tidly.modules.organization.usecase.company;

import java.util.List;

import org.springframework.stereotype.Service;

import no.tidly.modules.organization.domain.CompanyEntity;
import no.tidly.modules.organization.repository.CompanyRepository;

@Service
public class GetAllCompaniesUseCase {

    private final CompanyRepository companyRepository;

    public GetAllCompaniesUseCase(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<CompanyEntity> execute() {
        return this.companyRepository.findAll();
    }
}
