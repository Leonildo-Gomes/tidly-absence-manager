package no.tidly.modules.organization.usecase.team;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import no.tidly.modules.organization.dto.TeamResponse;
import no.tidly.modules.organization.mapper.TeamMapper;
import no.tidly.modules.organization.repository.TeamRepository;

@Service
@RequiredArgsConstructor
public class GetTeamsByDepartmentIdUseCase {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    public List<TeamResponse> execute(UUID departmentId) {
        return this.teamRepository.findAllByDepartmentId(departmentId).stream()
                .map(this.teamMapper::toResponse)
                .toList();
    }
}
