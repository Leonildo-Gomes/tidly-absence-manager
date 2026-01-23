package no.tidly.modules.organization.usecase.employee;

import java.util.UUID;

import org.springframework.stereotype.Service;

import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.core.shared.Utils;
import no.tidly.modules.organization.domain.EmployeeEntity;
import no.tidly.modules.organization.dto.EmployeeRequest;
import no.tidly.modules.organization.repository.CompanyRepository;
import no.tidly.modules.organization.repository.EmployeeRepository;
import no.tidly.modules.organization.repository.TeamRepository;

@Service
public class UpdateEmployeeUseCase {

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;
    private final TeamRepository teamRepository;

    public UpdateEmployeeUseCase(EmployeeRepository employeeRepository,
            CompanyRepository companyRepository,
            TeamRepository teamRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
        this.teamRepository = teamRepository;
    }

    public EmployeeEntity execute(UUID id, EmployeeRequest request) {
        var employee = this.employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        Utils.copyNonNullProperties(request, employee);
        return this.employeeRepository.save(employee);
    }
}
