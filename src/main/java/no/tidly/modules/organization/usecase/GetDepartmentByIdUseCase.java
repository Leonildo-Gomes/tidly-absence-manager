package no.tidly.modules.organization.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.modules.organization.domain.DepartmentEntity;
import no.tidly.modules.organization.repository.DepartmentRepository;

@Service
public class GetDepartmentByIdUseCase {

    private final DepartmentRepository departmentRepository;

    public GetDepartmentByIdUseCase(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public DepartmentEntity execute(UUID id) {
        return this.departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
    }
}
