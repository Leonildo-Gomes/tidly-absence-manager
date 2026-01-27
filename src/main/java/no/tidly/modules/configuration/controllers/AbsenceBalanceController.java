package no.tidly.modules.configuration.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import no.tidly.modules.configuration.dto.AbsenceBalanceRequest;
import no.tidly.modules.configuration.dto.AbsenceBalanceResponse;
import no.tidly.modules.configuration.usecase.absencebalance.CreateAbsenceBalanceUseCase;
import no.tidly.modules.configuration.usecase.absencebalance.DeleteAbsenceBalanceUseCase;
import no.tidly.modules.configuration.usecase.absencebalance.GetAbsenceBalanceByIdUseCase;
import no.tidly.modules.configuration.usecase.absencebalance.GetAbsenceBalancesByEmployeeUseCase;
import no.tidly.modules.configuration.usecase.absencebalance.UpdateAbsenceBalanceUseCase;

@RestController
@RequestMapping("/api/v1/absence-balances")
@RequiredArgsConstructor
public class AbsenceBalanceController {

    private final CreateAbsenceBalanceUseCase createAbsenceBalanceUseCase;
    private final GetAbsenceBalanceByIdUseCase getAbsenceBalanceByIdUseCase;
    private final GetAbsenceBalancesByEmployeeUseCase getAbsenceBalancesByEmployeeUseCase;
    private final UpdateAbsenceBalanceUseCase updateAbsenceBalanceUseCase;
    private final DeleteAbsenceBalanceUseCase deleteAbsenceBalanceUseCase;

    @PostMapping
    public ResponseEntity<AbsenceBalanceResponse> create(@Valid @RequestBody AbsenceBalanceRequest request) {
        AbsenceBalanceResponse response = createAbsenceBalanceUseCase.execute(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AbsenceBalanceResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(getAbsenceBalanceByIdUseCase.execute(id));
    }

    @GetMapping
    public ResponseEntity<List<AbsenceBalanceResponse>> getByEmployee(@RequestParam UUID employeeId) {
        return ResponseEntity.ok(getAbsenceBalancesByEmployeeUseCase.execute(employeeId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AbsenceBalanceResponse> update(@PathVariable UUID id,
            @Valid @RequestBody AbsenceBalanceRequest request) {
        return ResponseEntity.ok(updateAbsenceBalanceUseCase.execute(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteAbsenceBalanceUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
