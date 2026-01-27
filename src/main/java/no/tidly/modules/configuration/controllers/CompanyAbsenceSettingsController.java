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
import no.tidly.modules.configuration.dto.CompanyAbsenceSettingsRequest;
import no.tidly.modules.configuration.dto.CompanyAbsenceSettingsResponse;
import no.tidly.modules.configuration.usecase.companyabsencesettings.CreateCompanyAbsenceSettingsUseCase;
import no.tidly.modules.configuration.usecase.companyabsencesettings.DeleteCompanyAbsenceSettingsUseCase;
import no.tidly.modules.configuration.usecase.companyabsencesettings.GetCompanyAbsenceSettingsByCompanyUseCase;
import no.tidly.modules.configuration.usecase.companyabsencesettings.GetCompanyAbsenceSettingsByIdUseCase;
import no.tidly.modules.configuration.usecase.companyabsencesettings.UpdateCompanyAbsenceSettingsUseCase;

@RestController
@RequestMapping("/api/v1/company-absence-settings")
@RequiredArgsConstructor
public class CompanyAbsenceSettingsController {

    private final CreateCompanyAbsenceSettingsUseCase createCompanyAbsenceSettingsUseCase;
    private final GetCompanyAbsenceSettingsByIdUseCase getCompanyAbsenceSettingsByIdUseCase;
    private final GetCompanyAbsenceSettingsByCompanyUseCase getCompanyAbsenceSettingsByCompanyUseCase;
    private final UpdateCompanyAbsenceSettingsUseCase updateCompanyAbsenceSettingsUseCase;
    private final DeleteCompanyAbsenceSettingsUseCase deleteCompanyAbsenceSettingsUseCase;

    @PostMapping
    public ResponseEntity<CompanyAbsenceSettingsResponse> create(
            @Valid @RequestBody CompanyAbsenceSettingsRequest request) {
        CompanyAbsenceSettingsResponse response = createCompanyAbsenceSettingsUseCase.execute(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyAbsenceSettingsResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(getCompanyAbsenceSettingsByIdUseCase.execute(id));
    }

    @GetMapping
    public ResponseEntity<List<CompanyAbsenceSettingsResponse>> getByCompany(@RequestParam UUID companyId) {
        return ResponseEntity.ok(getCompanyAbsenceSettingsByCompanyUseCase.execute(companyId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyAbsenceSettingsResponse> update(@PathVariable UUID id,
            @Valid @RequestBody CompanyAbsenceSettingsRequest request) {
        return ResponseEntity.ok(updateCompanyAbsenceSettingsUseCase.execute(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteCompanyAbsenceSettingsUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
