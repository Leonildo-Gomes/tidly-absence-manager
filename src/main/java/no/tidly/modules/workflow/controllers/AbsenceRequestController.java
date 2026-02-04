package no.tidly.modules.workflow.controllers;

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
import lombok.RequiredArgsConstructor;
import no.tidly.modules.workflow.dto.AbsenceRequestRequest;
import no.tidly.modules.workflow.dto.AbsenceRequestResponse;
import no.tidly.modules.workflow.usecase.absencerequest.CreateAbsenceRequestUseCase;
import no.tidly.modules.workflow.usecase.absencerequest.DeleteAbsenceRequestUseCase;
import no.tidly.modules.workflow.usecase.absencerequest.GetAbsenceRequestByIdUseCase;
import no.tidly.modules.workflow.usecase.absencerequest.UpdateAbsenceRequestUseCase;

@RestController
@RequestMapping("/api/v1/absence-requests")
@RequiredArgsConstructor
public class AbsenceRequestController {

    private final CreateAbsenceRequestUseCase createUseCase;
    private final GetAbsenceRequestByIdUseCase getByIdUseCase;
    private final UpdateAbsenceRequestUseCase updateUseCase;
    private final DeleteAbsenceRequestUseCase deleteUseCase;

    @PostMapping
    public ResponseEntity<AbsenceRequestResponse> create(@RequestBody @Valid AbsenceRequestRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.createUseCase.execute(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AbsenceRequestResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(this.getByIdUseCase.execute(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AbsenceRequestResponse> update(@PathVariable UUID id,
            @RequestBody @Valid AbsenceRequestRequest request) {
        return ResponseEntity.ok(this.updateUseCase.execute(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.deleteUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
