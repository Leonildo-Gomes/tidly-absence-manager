package no.tidly.modules.organization.usecase.department;

import java.util.List;

import org.springframework.stereotype.Service;

import no.tidly.modules.organization.dto.DepartmentResponse;
import no.tidly.modules.organization.mapper.DepartmentMapper;
import no.tidly.modules.organization.repository.DepartmentRepository;

@Service
public class GetAllDepartmentsUseCase {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public GetAllDepartmentsUseCase(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    public List<DepartmentResponse> execute() {
        return this.departmentRepository.findAll().stream()
                .map(this.departmentMapper::toResponse)
                .toList();
    }
}
