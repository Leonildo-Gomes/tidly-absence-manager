package no.tidly.modules.organization.usecase.team;

import java.util.List;

import org.springframework.stereotype.Service;

import no.tidly.modules.organization.domain.TeamEntity;
import no.tidly.modules.organization.repository.TeamRepository;

@Service
public class GetAllTeamsUseCase {

    private final TeamRepository teamRepository;

    public GetAllTeamsUseCase(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<TeamEntity> execute() {
        return this.teamRepository.findAll();
    }
}
