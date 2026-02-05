package no.tidly.modules.organization.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import no.tidly.modules.organization.dto.AssignManagerRequest;
import no.tidly.modules.organization.dto.DepartmentManagerHistoryResponse;
import no.tidly.modules.organization.dto.DepartmentRequest;
import no.tidly.modules.organization.dto.DepartmentResponse;
import no.tidly.modules.organization.mapper.DepartmentMapper;
import no.tidly.modules.organization.usecase.department.AssignDepartmentManagerUseCase;
import no.tidly.modules.organization.usecase.department.CreateDepartmentUseCase;
import no.tidly.modules.organization.usecase.department.DeleteDepartmentUseCase;
import no.tidly.modules.organization.usecase.department.GetAllDepartmentsUseCase;
import no.tidly.modules.organization.usecase.department.GetDepartmentByIdUseCase;
import no.tidly.modules.organization.usecase.department.GetDepartmentManagerHistoryUseCase;
import no.tidly.modules.organization.usecase.department.UpdateDepartmentUseCase;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    private final CreateDepartmentUseCase createDepartmentUseCase;
    private final GetDepartmentByIdUseCase getDepartmentByIdUseCase;
    private final GetAllDepartmentsUseCase getAllDepartmentsUseCase;
    private final UpdateDepartmentUseCase updateDepartmentUseCase;
    private final DeleteDepartmentUseCase deleteDepartmentUseCase;
    private final AssignDepartmentManagerUseCase assignDepartmentManagerUseCase;
    private final GetDepartmentManagerHistoryUseCase getDepartmentManagerHistoryUseCase;
    private final DepartmentMapper departmentMapper;

    public DepartmentController(CreateDepartmentUseCase createDepartmentUseCase,
            GetDepartmentByIdUseCase getDepartmentByIdUseCase,
            GetAllDepartmentsUseCase getAllDepartmentsUseCase,
            UpdateDepartmentUseCase updateDepartmentUseCase,
            DeleteDepartmentUseCase deleteDepartmentUseCase,
            AssignDepartmentManagerUseCase assignDepartmentManagerUseCase,
            GetDepartmentManagerHistoryUseCase getDepartmentManagerHistoryUseCase,
            DepartmentMapper departmentMapper) {
        this.createDepartmentUseCase = createDepartmentUseCase;
        this.getDepartmentByIdUseCase = getDepartmentByIdUseCase;
        this.getAllDepartmentsUseCase = getAllDepartmentsUseCase;
        this.updateDepartmentUseCase = updateDepartmentUseCase;
        this.deleteDepartmentUseCase = deleteDepartmentUseCase;
        this.assignDepartmentManagerUseCase = assignDepartmentManagerUseCase;
        this.getDepartmentManagerHistoryUseCase = getDepartmentManagerHistoryUseCase;
        this.departmentMapper = departmentMapper;
    }

    @PostMapping
    public ResponseEntity<DepartmentResponse> create(@Valid @RequestBody DepartmentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.createDepartmentUseCase.execute(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getById(@PathVariable UUID id) {
        var department = this.getDepartmentByIdUseCase.execute(id);
        return ResponseEntity.ok(this.departmentMapper.toResponse(department));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> getAll() {
        var departments = this.getAllDepartmentsUseCase.execute();
        var response = departments.stream().map(this.departmentMapper::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> update(@PathVariable UUID id,
            @Valid @RequestBody DepartmentRequest request) {
        var department = this.updateDepartmentUseCase.execute(id, request);
        return ResponseEntity.ok(this.departmentMapper.toResponse(department));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.deleteDepartmentUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/manager")
    public ResponseEntity<Void> assignManager(@PathVariable UUID id, @Valid @RequestBody AssignManagerRequest request) {
        this.assignDepartmentManagerUseCase.execute(id, request.managerId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<List<DepartmentManagerHistoryResponse>> getHistory(@PathVariable UUID id) {
        var history = this.getDepartmentManagerHistoryUseCase.execute(id);
        var response = history.stream()
                .map(h -> new DepartmentManagerHistoryResponse(
                        h.getId(),
                        h.getDepartment().getId(),
                        h.getDepartment().getName(),
                        h.getManager().getId(),
                        h.getManager().getName(),
                        h.getStartDate(),
                        h.getEndDate()))
                .toList();
        return ResponseEntity.ok(response);
    }
}
