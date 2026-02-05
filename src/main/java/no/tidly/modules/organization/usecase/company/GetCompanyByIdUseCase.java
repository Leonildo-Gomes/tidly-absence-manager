package no.tidly.modules.organization.usecase.company;

import java.util.UUID;

import org.springframework.stereotype.Service;

import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.modules.organization.dto.CompanyResponse;
import no.tidly.modules.organization.mapper.CompanyMapper;
import no.tidly.modules.organization.repository.CompanyRepository;

@Service
public class GetCompanyByIdUseCase {

    private final CompanyRepository companyRepository;
    private final CompanyMapper mapper;

    public GetCompanyByIdUseCase(CompanyRepository companyRepository, CompanyMapper mapper) {
        this.companyRepository = companyRepository;
        this.mapper = mapper;
    }

    public CompanyResponse execute(UUID id) {
        return this.companyRepository.findById(id)
                .map(this.mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
    }
}
