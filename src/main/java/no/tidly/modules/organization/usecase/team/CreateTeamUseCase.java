package no.tidly.modules.organization.usecase.team;

import org.springframework.stereotype.Service;

import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.modules.organization.domain.DepartmentEntity;
import no.tidly.modules.organization.domain.TeamEntity;
import no.tidly.modules.organization.dto.TeamRequest;
import no.tidly.modules.organization.repository.DepartmentRepository;
import no.tidly.modules.organization.repository.TeamRepository;

@Service
public class CreateTeamUseCase {

    private final TeamRepository teamRepository;
    private final DepartmentRepository departmentRepository;

    public CreateTeamUseCase(TeamRepository teamRepository, DepartmentRepository departmentRepository) {
        this.teamRepository = teamRepository;
        this.departmentRepository = departmentRepository;
    }

    public TeamEntity execute(TeamRequest request) {
        DepartmentEntity department = this.departmentRepository.findById(request.departmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        var team = TeamEntity.builder()
                .name(request.name())
                .department(department)
                .build();
        return this.teamRepository.save(team);
    }
}
