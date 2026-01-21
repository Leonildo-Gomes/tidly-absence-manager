package no.tidly.modules.organization.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.modules.organization.domain.DepartmentEntity;
import no.tidly.modules.organization.domain.TeamEntity;
import no.tidly.modules.organization.dto.TeamRequest;
import no.tidly.modules.organization.repository.DepartmentRepository;
import no.tidly.modules.organization.repository.TeamRepository;

@Service
public class UpdateTeamUseCase {

    private final TeamRepository teamRepository;
    private final DepartmentRepository departmentRepository;

    public UpdateTeamUseCase(TeamRepository teamRepository, DepartmentRepository departmentRepository) {
        this.teamRepository = teamRepository;
        this.departmentRepository = departmentRepository;
    }

    public TeamEntity execute(UUID id, TeamRequest request) {
        TeamEntity team = this.teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found"));

        DepartmentEntity department = this.departmentRepository.findById(request.departmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        team.setName(request.name());
        team.setDepartment(department);

        return this.teamRepository.save(team);
    }
}
