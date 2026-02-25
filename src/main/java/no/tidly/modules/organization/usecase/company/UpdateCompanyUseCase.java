package no.tidly.modules.organization.usecase.company;

import java.util.UUID;

import org.springframework.stereotype.Service;

import no.tidly.core.exceptions.ForbiddenAccessException;
import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.core.security.SecurityContextService;
import no.tidly.core.shared.Utils;
import no.tidly.modules.organization.dto.CompanyRequest;
import no.tidly.modules.organization.dto.CompanyResponse;
import no.tidly.modules.organization.mapper.CompanyMapper;
import no.tidly.modules.organization.repository.CompanyRepository;

@Service
public class UpdateCompanyUseCase {

    private final CompanyRepository companyRepository;
    private final CompanyMapper mapper;
    private final SecurityContextService securityContextService;

    public UpdateCompanyUseCase(CompanyRepository companyRepository, CompanyMapper mapper,
            SecurityContextService securityContextService) {
        this.companyRepository = companyRepository;
        this.mapper = mapper;
        this.securityContextService = securityContextService;
    }

    public CompanyResponse execute(UUID id, CompanyRequest request) {
        String activeClerkOrgId = securityContextService.getCurrentOrganizationId();

        var company = this.companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        if (activeClerkOrgId == null || !activeClerkOrgId.equals(company.getClerkOrgId())) {
            throw new ForbiddenAccessException("O utilizador não tem permissão para atualizar esta empresa.");
        }

        Utils.copyNonNullProperties(request, company);
        var updatedEntity = this.companyRepository.save(company);
        return this.mapper.toResponse(updatedEntity);
    }
}
