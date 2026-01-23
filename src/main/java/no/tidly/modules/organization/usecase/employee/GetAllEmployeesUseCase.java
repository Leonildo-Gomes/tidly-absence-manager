package no.tidly.modules.organization.usecase.employee;

import java.util.List;

import org.springframework.stereotype.Service;

import no.tidly.modules.organization.domain.EmployeeEntity;
import no.tidly.modules.organization.repository.EmployeeRepository;

@Service
public class GetAllEmployeesUseCase {

    private final EmployeeRepository employeeRepository;

    public GetAllEmployeesUseCase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeEntity> execute() {
        return this.employeeRepository.findAll();
    }
}
