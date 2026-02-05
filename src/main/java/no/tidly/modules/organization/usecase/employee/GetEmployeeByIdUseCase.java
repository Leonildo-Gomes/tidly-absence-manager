package no.tidly.modules.organization.usecase.employee;

import java.util.UUID;

import org.springframework.stereotype.Service;

import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.modules.organization.dto.EmployeeResponse;
import no.tidly.modules.organization.mapper.EmployeeMapper;
import no.tidly.modules.organization.repository.EmployeeRepository;

@Service
public class GetEmployeeByIdUseCase {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper mapper;

    public GetEmployeeByIdUseCase(EmployeeRepository employeeRepository, EmployeeMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    public EmployeeResponse execute(UUID id) {
        return this.employeeRepository.findById(id)
                .map(this.mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }
}
