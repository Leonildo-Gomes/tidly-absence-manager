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

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import no.tidly.modules.organization.dto.CompanyRequest;
import no.tidly.modules.organization.dto.CompanyResponse;
import no.tidly.modules.organization.usecase.company.CreateCompanyUseCase;
import no.tidly.modules.organization.usecase.company.DeleteCompanyUseCase;
import no.tidly.modules.organization.usecase.company.GetAllCompaniesUseCase;
import no.tidly.modules.organization.usecase.company.GetCompanyByIdUseCase;
import no.tidly.modules.organization.usecase.company.UpdateCompanyUseCase;

@RestController
@RequestMapping("/api/v1/companies")
@Tag(name = "Company", description = "Company management")
public class CompanyController {

    private final CreateCompanyUseCase createCompanyUseCase;
    private final GetCompanyByIdUseCase getCompanyByIdUseCase;
    private final GetAllCompaniesUseCase getAllCompaniesUseCase;
    private final UpdateCompanyUseCase updateCompanyUseCase;
    private final DeleteCompanyUseCase deleteCompanyUseCase;

    public CompanyController(CreateCompanyUseCase createCompanyUseCase,
            GetCompanyByIdUseCase getCompanyByIdUseCase,
            GetAllCompaniesUseCase getAllCompaniesUseCase,
            UpdateCompanyUseCase updateCompanyUseCase,
            DeleteCompanyUseCase deleteCompanyUseCase) {
        this.createCompanyUseCase = createCompanyUseCase;
        this.getCompanyByIdUseCase = getCompanyByIdUseCase;
        this.getAllCompaniesUseCase = getAllCompaniesUseCase;
        this.updateCompanyUseCase = updateCompanyUseCase;
        this.deleteCompanyUseCase = deleteCompanyUseCase;
    }

    @PostMapping
    public ResponseEntity<CompanyResponse> create(@Valid @RequestBody CompanyRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.createCompanyUseCase.execute(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(this.getCompanyByIdUseCase.execute(id));
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getAll() {
        return ResponseEntity.ok(this.getAllCompaniesUseCase.execute());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> update(@PathVariable UUID id,
            @Valid @RequestBody CompanyRequest request) {
        return ResponseEntity.ok(this.updateCompanyUseCase.execute(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.deleteCompanyUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
