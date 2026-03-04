package no.tidly.modules.organization.usecase.team;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.core.security.SecurityContextService;
import no.tidly.modules.organization.domain.CompanyEntity;
import no.tidly.modules.organization.dto.TeamResponse;
import no.tidly.modules.organization.mapper.TeamMapper;
import no.tidly.modules.organization.repository.CompanyRepository;
import no.tidly.modules.organization.repository.TeamRepository;

@Service
@RequiredArgsConstructor
public class GetAllTeamsUseCase {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final SecurityContextService securityContextService;
    private final CompanyRepository companyRepository;

    public List<TeamResponse> execute() {
        String activeClerkOrgId = securityContextService.getCurrentOrganizationId();
        if (activeClerkOrgId == null) {
            return List.of();
        }
        CompanyEntity company = this.companyRepository.findByClerkOrgId(activeClerkOrgId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        return this.teamRepository.findAllByCompanyId(company.getId()).stream()
                .map(this.teamMapper::toResponse)
                .toList();
    }
}
