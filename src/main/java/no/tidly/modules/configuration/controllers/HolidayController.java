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
import no.tidly.modules.configuration.dto.CreateHolidayRequest;
import no.tidly.modules.configuration.dto.HolidayResponse;
import no.tidly.modules.configuration.dto.UpdateHolidayRequest;
import no.tidly.modules.configuration.usecase.holiday.CreateHolidayUseCase;
import no.tidly.modules.configuration.usecase.holiday.DeleteHolidayUseCase;
import no.tidly.modules.configuration.usecase.holiday.GetAllHolidaysUseCase;
import no.tidly.modules.configuration.usecase.holiday.GetHolidayByIdUseCase;
import no.tidly.modules.configuration.usecase.holiday.UpdateHolidayUseCase;

@RestController
@RequestMapping("/api/v1/holidays")
@RequiredArgsConstructor
public class HolidayController {

    private final CreateHolidayUseCase createHolidayUseCase;
    private final GetHolidayByIdUseCase getHolidayByIdUseCase;
    private final GetAllHolidaysUseCase getAllHolidaysUseCase;
    private final UpdateHolidayUseCase updateHolidayUseCase;
    private final DeleteHolidayUseCase deleteHolidayUseCase;

    @PostMapping
    public ResponseEntity<HolidayResponse> createHoliday(@Valid @RequestBody CreateHolidayRequest request) {
        HolidayResponse response = createHolidayUseCase.execute(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HolidayResponse> getHolidayById(@PathVariable UUID id) {
        HolidayResponse response = getHolidayByIdUseCase.execute(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<HolidayResponse>> getAllHolidays(@RequestParam(required = false) UUID companyId) {
        // If companyId is null, it might return only global holidays or empty depending
        // on logic.
        // The use case is implemented to return both specific and global given a
        // companyId.
        // If companyId is NOT provided, maybe we should return only global holidays?
        // For now allowing null to pass to usecase which creates query "where companyId
        // = null or companyId is null" -> global only.
        List<HolidayResponse> response = getAllHolidaysUseCase.execute(companyId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HolidayResponse> updateHoliday(
            @PathVariable UUID id,
            @RequestBody UpdateHolidayRequest request) {
        HolidayResponse response = updateHolidayUseCase.execute(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHoliday(@PathVariable UUID id) {
        deleteHolidayUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
