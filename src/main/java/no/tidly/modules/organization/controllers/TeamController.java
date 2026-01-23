package no.tidly.modules.organization.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import no.tidly.modules.organization.domain.TeamEntity;
import no.tidly.modules.organization.dto.TeamRequest;
import no.tidly.modules.organization.dto.TeamResponse;
import no.tidly.modules.organization.usecase.team.CreateTeamUseCase;
import no.tidly.modules.organization.usecase.team.GetAllTeamsUseCase;
import no.tidly.modules.organization.usecase.team.GetTeamByIdUseCase;
import no.tidly.modules.organization.usecase.team.UpdateTeamUseCase;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final CreateTeamUseCase createTeamUseCase;
    private final GetTeamByIdUseCase getTeamByIdUseCase;
    private final GetAllTeamsUseCase getAllTeamsUseCase;
    private final UpdateTeamUseCase updateTeamUseCase;

    public TeamController(CreateTeamUseCase createTeamUseCase,
            GetTeamByIdUseCase getTeamByIdUseCase,
            GetAllTeamsUseCase getAllTeamsUseCase,
            UpdateTeamUseCase updateTeamUseCase) {
        this.createTeamUseCase = createTeamUseCase;
        this.getTeamByIdUseCase = getTeamByIdUseCase;
        this.getAllTeamsUseCase = getAllTeamsUseCase;
        this.updateTeamUseCase = updateTeamUseCase;
    }

    @PostMapping
    public ResponseEntity<TeamResponse> create(@Valid @RequestBody TeamRequest request) {
        var team = this.createTeamUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.mapToResponse(team));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamResponse> getById(@PathVariable UUID id) {
        var team = this.getTeamByIdUseCase.execute(id);
        return ResponseEntity.ok(this.mapToResponse(team));
    }

    @GetMapping
    public ResponseEntity<List<TeamResponse>> getAll() {
        var teams = this.getAllTeamsUseCase.execute();
        var response = teams.stream().map(this::mapToResponse).toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamResponse> update(@PathVariable UUID id,
            @Valid @RequestBody TeamRequest request) {
        var team = this.updateTeamUseCase.execute(id, request);
        return ResponseEntity.ok(this.mapToResponse(team));
    }

    private TeamResponse mapToResponse(TeamEntity team) {
        return new TeamResponse(
                team.getId(),
                team.getName(),
                team.getDepartment().getId(),
                team.getDepartment().getName());
    }
}
