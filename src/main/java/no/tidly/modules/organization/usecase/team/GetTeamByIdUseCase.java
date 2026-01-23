package no.tidly.modules.organization.usecase.team;

import java.util.UUID;

import org.springframework.stereotype.Service;

import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.modules.organization.domain.TeamEntity;
import no.tidly.modules.organization.repository.TeamRepository;

@Service
public class GetTeamByIdUseCase {

    private final TeamRepository teamRepository;

    public GetTeamByIdUseCase(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public TeamEntity execute(UUID id) {
        return this.teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found"));
    }
}
