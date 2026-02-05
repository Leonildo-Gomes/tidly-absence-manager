package no.tidly.modules.organization.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import no.tidly.modules.organization.dto.AssignLeaderRequest;
import no.tidly.modules.organization.dto.TeamLeaderHistoryResponse;
import no.tidly.modules.organization.dto.TeamRequest;
import no.tidly.modules.organization.dto.TeamResponse;
import no.tidly.modules.organization.usecase.team.AssignTeamLeaderUseCase;
import no.tidly.modules.organization.usecase.team.CreateTeamUseCase;
import no.tidly.modules.organization.usecase.team.DeleteTeamUseCase;
import no.tidly.modules.organization.usecase.team.GetAllTeamsUseCase;
import no.tidly.modules.organization.usecase.team.GetTeamByIdUseCase;
import no.tidly.modules.organization.usecase.team.GetTeamLeaderHistoryUseCase;
import no.tidly.modules.organization.usecase.team.UpdateTeamUseCase;

@RestController
@RequestMapping("/api/v1/teams")
public class TeamController {

    private final CreateTeamUseCase createTeamUseCase;
    private final GetTeamByIdUseCase getTeamByIdUseCase;
    private final GetAllTeamsUseCase getAllTeamsUseCase;
    private final UpdateTeamUseCase updateTeamUseCase;
    private final DeleteTeamUseCase deleteTeamUseCase;
    private final AssignTeamLeaderUseCase assignTeamLeaderUseCase;
    private final GetTeamLeaderHistoryUseCase getTeamLeaderHistoryUseCase;

    public TeamController(CreateTeamUseCase createTeamUseCase,
            GetTeamByIdUseCase getTeamByIdUseCase,
            GetAllTeamsUseCase getAllTeamsUseCase,
            UpdateTeamUseCase updateTeamUseCase,
            DeleteTeamUseCase deleteTeamUseCase,
            AssignTeamLeaderUseCase assignTeamLeaderUseCase,
            GetTeamLeaderHistoryUseCase getTeamLeaderHistoryUseCase) {
        this.createTeamUseCase = createTeamUseCase;
        this.getTeamByIdUseCase = getTeamByIdUseCase;
        this.getAllTeamsUseCase = getAllTeamsUseCase;
        this.updateTeamUseCase = updateTeamUseCase;
        this.deleteTeamUseCase = deleteTeamUseCase;
        this.assignTeamLeaderUseCase = assignTeamLeaderUseCase;
        this.getTeamLeaderHistoryUseCase = getTeamLeaderHistoryUseCase;
    }

    @PostMapping
    public ResponseEntity<TeamResponse> create(@Valid @RequestBody TeamRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.createTeamUseCase.execute(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(this.getTeamByIdUseCase.execute(id));
    }

    @GetMapping
    public ResponseEntity<List<TeamResponse>> getAll() {
        return ResponseEntity.ok(this.getAllTeamsUseCase.execute());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamResponse> update(@PathVariable UUID id,
            @Valid @RequestBody TeamRequest request) {
        return ResponseEntity.ok(this.updateTeamUseCase.execute(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.deleteTeamUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/leader")
    public ResponseEntity<Void> assignLeader(@PathVariable UUID id, @Valid @RequestBody AssignLeaderRequest request) {
        this.assignTeamLeaderUseCase.execute(id, request.leaderId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<List<TeamLeaderHistoryResponse>> getHistory(@PathVariable UUID id) {
        var history = this.getTeamLeaderHistoryUseCase.execute(id);
        var response = history.stream()
                .map(h -> new TeamLeaderHistoryResponse(
                        h.getId(),
                        h.getTeam().getId(),
                        h.getTeam().getName(),
                        h.getLeader().getId(),
                        h.getLeader().getName(),
                        h.getStartDate(),
                        h.getEndDate()))
                .toList();
        return ResponseEntity.ok(response);
    }

}
