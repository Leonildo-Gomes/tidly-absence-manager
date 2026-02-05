package no.tidly.modules.organization.usecase.department;

import java.util.UUID;

import org.springframework.stereotype.Service;

import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.modules.organization.dto.DepartmentResponse;
import no.tidly.modules.organization.mapper.DepartmentMapper;
import no.tidly.modules.organization.repository.DepartmentRepository;

@Service
public class GetDepartmentByIdUseCase {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public GetDepartmentByIdUseCase(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    public DepartmentResponse execute(UUID id) {
        return this.departmentRepository.findById(id)
                .map(this.departmentMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
    }
}
