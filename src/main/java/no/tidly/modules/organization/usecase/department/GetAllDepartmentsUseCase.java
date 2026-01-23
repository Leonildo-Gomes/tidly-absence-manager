package no.tidly.modules.organization.usecase.department;

import java.util.List;

import org.springframework.stereotype.Service;

import no.tidly.modules.organization.domain.DepartmentEntity;
import no.tidly.modules.organization.repository.DepartmentRepository;

@Service
public class GetAllDepartmentsUseCase {

    private final DepartmentRepository departmentRepository;

    public GetAllDepartmentsUseCase(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentEntity> execute() {
        return this.departmentRepository.findAll();
    }
}
