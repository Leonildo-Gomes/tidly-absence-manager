package no.tidly.modules.organization.usecase.company;

import java.util.List;

import org.springframework.stereotype.Service;

import no.tidly.modules.organization.dto.CompanyResponse;
import no.tidly.modules.organization.mapper.CompanyMapper;
import no.tidly.modules.organization.repository.CompanyRepository;

@Service
public class GetAllCompaniesUseCase {

    private final CompanyRepository companyRepository;
    private final CompanyMapper mapper;

    public GetAllCompaniesUseCase(CompanyRepository companyRepository, CompanyMapper mapper) {
        this.companyRepository = companyRepository;
        this.mapper = mapper;
    }

    public List<CompanyResponse> execute() {
        return this.companyRepository.findAll().stream()
                .map(this.mapper::toResponse)
                .toList();
    }
}
