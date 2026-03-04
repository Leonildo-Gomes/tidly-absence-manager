package no.tidly.modules.organization.usecase.employee;

import java.util.List;

import org.springframework.stereotype.Service;

import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.core.security.SecurityContextService;
import no.tidly.modules.organization.domain.CompanyEntity;
import no.tidly.modules.organization.dto.EmployeeResponse;
import no.tidly.modules.organization.mapper.EmployeeMapper;
import no.tidly.modules.organization.repository.CompanyRepository;
import no.tidly.modules.organization.repository.EmployeeRepository;

@Service
public class GetAllEmployeesUseCase {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper mapper;
    private final SecurityContextService securityContextService;
    private final CompanyRepository companyRepository;

    public GetAllEmployeesUseCase(EmployeeRepository employeeRepository, EmployeeMapper mapper,
            SecurityContextService securityContextService, CompanyRepository companyRepository) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
        this.securityContextService = securityContextService;
        this.companyRepository = companyRepository;
    }

    public List<EmployeeResponse> execute() {
        String activeClerkOrgId = securityContextService.getCurrentOrganizationId();
        if (activeClerkOrgId == null) {
            return List.of();
        }
        CompanyEntity company = this.companyRepository.findByClerkOrgId(activeClerkOrgId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        return this.employeeRepository.findAllByCompanyId(company.getId()).stream()
                .map(this.mapper::toResponse)
                .toList();
    }
}
