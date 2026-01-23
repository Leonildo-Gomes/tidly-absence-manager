package no.tidly.modules.organization.usecase.employee;

import java.util.UUID;

import org.springframework.stereotype.Service;

import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.modules.organization.domain.EmployeeEntity;
import no.tidly.modules.organization.repository.EmployeeRepository;

@Service
public class DeleteEmployeeUseCase {

    private final EmployeeRepository repository;

    public DeleteEmployeeUseCase(EmployeeRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID id) {
        EmployeeEntity employee = this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employee.setIsActive(false);
        
        this.repository.save(employee);
    }
}
