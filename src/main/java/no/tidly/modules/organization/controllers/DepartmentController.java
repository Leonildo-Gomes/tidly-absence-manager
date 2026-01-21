package no.tidly.modules.organization.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import no.tidly.modules.organization.domain.DepartmentEntity;
import no.tidly.modules.organization.dto.DepartmentRequest;
import no.tidly.modules.organization.dto.DepartmentResponse;
import no.tidly.modules.organization.usecase.CreateDepartmentUseCase;
import no.tidly.modules.organization.usecase.GetAllDepartmentsUseCase;
import no.tidly.modules.organization.usecase.GetDepartmentByIdUseCase;
import no.tidly.modules.organization.usecase.UpdateDepartmentUseCase;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final CreateDepartmentUseCase createDepartmentUseCase;
    private final GetDepartmentByIdUseCase getDepartmentByIdUseCase;
    private final GetAllDepartmentsUseCase getAllDepartmentsUseCase;
    private final UpdateDepartmentUseCase updateDepartmentUseCase;

    public DepartmentController(CreateDepartmentUseCase createDepartmentUseCase,
            GetDepartmentByIdUseCase getDepartmentByIdUseCase,
            GetAllDepartmentsUseCase getAllDepartmentsUseCase,
            UpdateDepartmentUseCase updateDepartmentUseCase) {
        this.createDepartmentUseCase = createDepartmentUseCase;
        this.getDepartmentByIdUseCase = getDepartmentByIdUseCase;
        this.getAllDepartmentsUseCase = getAllDepartmentsUseCase;
        this.updateDepartmentUseCase = updateDepartmentUseCase;
    }

    @PostMapping
    public ResponseEntity<DepartmentResponse> create(@Valid @RequestBody DepartmentRequest request) {
        var department = this.createDepartmentUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.mapToResponse(department));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getById(@PathVariable UUID id) {
        var department = this.getDepartmentByIdUseCase.execute(id);
        return ResponseEntity.ok(this.mapToResponse(department));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> getAll() {
        var departments = this.getAllDepartmentsUseCase.execute();
        var response = departments.stream().map(this::mapToResponse).toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> update(@PathVariable UUID id,
            @Valid @RequestBody DepartmentRequest request) {
        var department = this.updateDepartmentUseCase.execute(id, request);
        return ResponseEntity.ok(this.mapToResponse(department));
    }

    private DepartmentResponse mapToResponse(DepartmentEntity department) {
        return new DepartmentResponse(
                department.getId(),
                department.getName(),
                department.getCode(),
                department.getCompany().getId(),
                department.getParentDepartment() != null ? department.getParentDepartment().getId() : null,
                department.getCreatedAt(),
                department.getUpdatedAt());
    }
}
