package no.tidly.modules.organization.mapper;

import org.springframework.stereotype.Component;

import no.tidly.modules.organization.domain.TeamEntity;
import no.tidly.modules.organization.dto.TeamResponse;

@Component
public class TeamMapper {
    public TeamResponse toResponse(TeamEntity team) {
        return new TeamResponse(
                team.getId(),
                team.getName(),
                team.getCode(),
                team.getDepartment().getId(),
                team.getDepartment().getName());
    }
}
