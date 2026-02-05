package no.tidly.modules.organization.usecase.team;

import java.util.List;

import org.springframework.stereotype.Service;

import no.tidly.modules.organization.dto.TeamResponse;
import no.tidly.modules.organization.mapper.TeamMapper;
import no.tidly.modules.organization.repository.TeamRepository;

@Service
public class GetAllTeamsUseCase {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    public GetAllTeamsUseCase(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    public List<TeamResponse> execute() {
        return this.teamRepository.findAll().stream()
                .map(this.teamMapper::toResponse)
                .toList();
    }
}
