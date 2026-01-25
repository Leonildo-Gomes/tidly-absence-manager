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
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import no.tidly.modules.configuration.dto.AbsenceTypeRequest;
import no.tidly.modules.configuration.dto.AbsenceTypeResponse;
import no.tidly.modules.configuration.usecase.absencetype.CreateAbsenceTypeUseCase;
import no.tidly.modules.configuration.usecase.absencetype.DeleteAbsenceTypeUseCase;
import no.tidly.modules.configuration.usecase.absencetype.GetAbsenceTypeByIdUseCase;
import no.tidly.modules.configuration.usecase.absencetype.GetAllAbsenceTypesUseCase;
import no.tidly.modules.configuration.usecase.absencetype.UpdateAbsenceTypeUseCase;

@RestController
@RequestMapping("/api/v1/absence-types")
@RequiredArgsConstructor
public class AbsenceTypeController {

    private final CreateAbsenceTypeUseCase createAbsenceTypeUseCase;
    private final GetAbsenceTypeByIdUseCase getAbsenceTypeByIdUseCase;
    private final GetAllAbsenceTypesUseCase getAllAbsenceTypesUseCase;
    private final UpdateAbsenceTypeUseCase updateAbsenceTypeUseCase;
    private final DeleteAbsenceTypeUseCase deleteAbsenceTypeUseCase;

    @PostMapping
    public ResponseEntity<AbsenceTypeResponse> create(@Valid @RequestBody AbsenceTypeRequest request) {
        AbsenceTypeResponse response = createAbsenceTypeUseCase.execute(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AbsenceTypeResponse>> getAll() {
        return ResponseEntity.ok(getAllAbsenceTypesUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AbsenceTypeResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(getAbsenceTypeByIdUseCase.execute(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AbsenceTypeResponse> update(@PathVariable UUID id,
            @Valid @RequestBody AbsenceTypeRequest request) {
        return ResponseEntity.ok(updateAbsenceTypeUseCase.execute(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteAbsenceTypeUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
