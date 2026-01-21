package no.tidly.modules.organization.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.modules.organization.domain.DepartmentEntity;
import no.tidly.modules.organization.dto.DepartmentRequest;
import no.tidly.modules.organization.repository.DepartmentRepository;

@Service
public class UpdateDepartmentUseCase {

    private final DepartmentRepository departmentRepository;

    public UpdateDepartmentUseCase(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public DepartmentEntity execute(UUID id, DepartmentRequest request) {
        var department = this.departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        department.setName(request.name());
        department.setCode(request.code());

        // Note: Changing company or parent department might require more
        // logic/validation
        // For now, we update basic fields.

        return this.departmentRepository.save(department);
    }
}
