package no.tidly.modules.organization.controllers;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import no.tidly.modules.organization.dto.JobTitleRequest;
import no.tidly.modules.organization.dto.JobTitleResponse;
import no.tidly.modules.organization.usecase.jobtitle.CreateJobTitleUseCase;
import no.tidly.modules.organization.usecase.jobtitle.DeleteJobTitleUseCase;
import no.tidly.modules.organization.usecase.jobtitle.GetAllJobTitlesUseCase;
import no.tidly.modules.organization.usecase.jobtitle.GetJobTitleByIdUseCase;
import no.tidly.modules.organization.usecase.jobtitle.UpdateJobTitleUseCase;

@RestController
@RequestMapping("/api/v1/job-titles")
@RequiredArgsConstructor
@Tag(name = "Job Titles", description = "Job title management")
public class JobTitleController {

    private final CreateJobTitleUseCase createJobTitleUseCase;
    private final GetJobTitleByIdUseCase getJobTitleByIdUseCase;
    private final GetAllJobTitlesUseCase getAllJobTitlesUseCase;
    private final UpdateJobTitleUseCase updateJobTitleUseCase;
    private final DeleteJobTitleUseCase deleteJobTitleUseCase;

    @Operation(summary = "Create a new job title", description = "Creates a new job title with the provided details.")
    @PostMapping
    public ResponseEntity<JobTitleResponse> create(@Valid @RequestBody JobTitleRequest request) {
        var response = createJobTitleUseCase.execute(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @Operation(summary = "Get job title by ID", description = "Retrieves a job title by its unique identifier.")
    @GetMapping("/{id}")
    public ResponseEntity<JobTitleResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(getJobTitleByIdUseCase.execute(id));
    }

    @Operation(summary = "Get all job titles", description = "Retrieves a list of all job titles.")
    @GetMapping
    public ResponseEntity<List<JobTitleResponse>> getAll() {
        return ResponseEntity.ok(getAllJobTitlesUseCase.execute());
    }

    @Operation(summary = "Update a job title", description = "Updates an existing job title with the provided details.")
    @PutMapping("/{id}")
    public ResponseEntity<JobTitleResponse> update(@PathVariable UUID id, @Valid @RequestBody JobTitleRequest request) {
        return ResponseEntity.ok(updateJobTitleUseCase.execute(id, request));
    }

    @Operation(summary = "Delete a job title", description = "Deletes a job title by its unique identifier.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteJobTitleUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
