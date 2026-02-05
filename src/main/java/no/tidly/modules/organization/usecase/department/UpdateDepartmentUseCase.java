package no.tidly.modules.organization.usecase.department;

import java.util.UUID;

import org.springframework.stereotype.Service;

import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.core.shared.Utils;
import no.tidly.modules.organization.dto.DepartmentRequest;
import no.tidly.modules.organization.dto.DepartmentResponse;
import no.tidly.modules.organization.mapper.DepartmentMapper;
import no.tidly.modules.organization.repository.DepartmentRepository;

@Service
public class UpdateDepartmentUseCase {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper mapper;

    public UpdateDepartmentUseCase(DepartmentRepository departmentRepository, DepartmentMapper mapper) {
        this.departmentRepository = departmentRepository;
        this.mapper = mapper;
    }

    public DepartmentResponse execute(UUID id, DepartmentRequest request) {
        var department = this.departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
        Utils.copyNonNullProperties(request, department);
        var updatedEntity = this.departmentRepository.save(department);
        return this.mapper.toResponse(updatedEntity);
    }
}
