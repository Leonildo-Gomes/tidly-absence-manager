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
public class JobTitleController {

    private final CreateJobTitleUseCase createJobTitleUseCase;
    private final GetJobTitleByIdUseCase getJobTitleByIdUseCase;
    private final GetAllJobTitlesUseCase getAllJobTitlesUseCase;
    private final UpdateJobTitleUseCase updateJobTitleUseCase;
    private final DeleteJobTitleUseCase deleteJobTitleUseCase;

    @PostMapping
    public ResponseEntity<JobTitleResponse> create(@Valid @RequestBody JobTitleRequest request) {
        var response = createJobTitleUseCase.execute(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobTitleResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(getJobTitleByIdUseCase.execute(id));
    }

    @GetMapping
    public ResponseEntity<List<JobTitleResponse>> getAll() {
        return ResponseEntity.ok(getAllJobTitlesUseCase.execute());
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobTitleResponse> update(@PathVariable UUID id, @Valid @RequestBody JobTitleRequest request) {
        return ResponseEntity.ok(updateJobTitleUseCase.execute(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteJobTitleUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
