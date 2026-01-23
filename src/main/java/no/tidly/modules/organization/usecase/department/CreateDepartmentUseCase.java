package no.tidly.modules.organization.usecase.department;

import org.springframework.stereotype.Service;

import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.modules.organization.domain.DepartmentEntity;
import no.tidly.modules.organization.dto.DepartmentRequest;
import no.tidly.modules.organization.repository.CompanyRepository;
import no.tidly.modules.organization.repository.DepartmentRepository;

@Service
public class CreateDepartmentUseCase {

    private final DepartmentRepository departmentRepository;
    private final CompanyRepository companyRepository;

    public CreateDepartmentUseCase(DepartmentRepository departmentRepository,
            CompanyRepository companyRepository) {
        this.departmentRepository = departmentRepository;
        this.companyRepository = companyRepository;
    }

    public DepartmentEntity execute(DepartmentRequest request) {
        var company = this.companyRepository.findById(request.companyId())
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        DepartmentEntity parentDepartment = null;
        if (request.parentDepartmentId() != null) {
            parentDepartment = this.departmentRepository.findById(request.parentDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent department not found"));
        }

        var department = DepartmentEntity.builder()
                .name(request.name())
                .code(request.code())
                .company(company)
                .parentDepartment(parentDepartment)
                .build();

        return this.departmentRepository.save(department);
    }
}
