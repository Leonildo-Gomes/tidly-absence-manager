package no.tidly.modules.organization.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import no.tidly.modules.organization.dto.EmployeeRequest;
import no.tidly.modules.organization.dto.EmployeeResponse;
import no.tidly.modules.organization.usecase.employee.CreateEmployeeUseCase;
import no.tidly.modules.organization.usecase.employee.DeleteEmployeeUseCase;
import no.tidly.modules.organization.usecase.employee.GetAllEmployeesUseCase;
import no.tidly.modules.organization.usecase.employee.GetEmployeeByIdUseCase;
import no.tidly.modules.organization.usecase.employee.UpdateEmployeeUseCase;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final CreateEmployeeUseCase createEmployeeUseCase;
    private final GetEmployeeByIdUseCase getEmployeeByIdUseCase;
    private final GetAllEmployeesUseCase getAllEmployeesUseCase;
    private final UpdateEmployeeUseCase updateEmployeeUseCase;
    private final DeleteEmployeeUseCase deleteEmployeeUseCase;

    public EmployeeController(CreateEmployeeUseCase createEmployeeUseCase,
            GetEmployeeByIdUseCase getEmployeeByIdUseCase,
            GetAllEmployeesUseCase getAllEmployeesUseCase,
            UpdateEmployeeUseCase updateEmployeeUseCase,
            DeleteEmployeeUseCase deleteEmployeeUseCase) {
        this.createEmployeeUseCase = createEmployeeUseCase;
        this.getEmployeeByIdUseCase = getEmployeeByIdUseCase;
        this.getAllEmployeesUseCase = getAllEmployeesUseCase;
        this.updateEmployeeUseCase = updateEmployeeUseCase;
        this.deleteEmployeeUseCase = deleteEmployeeUseCase;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> create(@Valid @RequestBody EmployeeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.createEmployeeUseCase.execute(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(this.getEmployeeByIdUseCase.execute(id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAll() {
        return ResponseEntity.ok(this.getAllEmployeesUseCase.execute());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> update(@PathVariable UUID id,
            @Valid @RequestBody EmployeeRequest request) {
        return ResponseEntity.ok(this.updateEmployeeUseCase.execute(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.deleteEmployeeUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
