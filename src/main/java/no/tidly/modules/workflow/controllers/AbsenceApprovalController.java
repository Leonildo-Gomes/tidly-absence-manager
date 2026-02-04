package no.tidly.modules.workflow.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import no.tidly.modules.workflow.dto.AbsenceApprovalRequest;
import no.tidly.modules.workflow.dto.AbsenceApprovalResponse;
import no.tidly.modules.workflow.usecase.absenceapproval.CreateAbsenceApprovalUseCase;
import no.tidly.modules.workflow.usecase.absenceapproval.GetAbsenceApprovalsByRequestUseCase;

@RestController
@RequestMapping("/api/v1/absence-approvals")
@RequiredArgsConstructor
public class AbsenceApprovalController {

    private final CreateAbsenceApprovalUseCase createUseCase;
    private final GetAbsenceApprovalsByRequestUseCase getByRequestUseCase;

    @PostMapping
    public ResponseEntity<AbsenceApprovalResponse> create(@RequestBody @Valid AbsenceApprovalRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createUseCase.execute(request));
    }

    @GetMapping("/request/{absenceRequestId}")
    public ResponseEntity<List<AbsenceApprovalResponse>> getByRequestId(@PathVariable UUID absenceRequestId) {
        return ResponseEntity.ok(getByRequestUseCase.execute(absenceRequestId));
    }
}
