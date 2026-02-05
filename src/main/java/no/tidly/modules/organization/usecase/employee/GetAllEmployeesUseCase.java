package no.tidly.modules.organization.usecase.employee;

import java.util.List;

import org.springframework.stereotype.Service;

import no.tidly.modules.organization.dto.EmployeeResponse;
import no.tidly.modules.organization.mapper.EmployeeMapper;
import no.tidly.modules.organization.repository.EmployeeRepository;

@Service
public class GetAllEmployeesUseCase {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper mapper;

    public GetAllEmployeesUseCase(EmployeeRepository employeeRepository, EmployeeMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    public List<EmployeeResponse> execute() {
        return this.employeeRepository.findAll().stream()
                .map(this.mapper::toResponse)
                .toList();
    }
}
