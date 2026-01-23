package no.tidly.modules.organization.usecase.department;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.modules.organization.domain.DepartmentManagerHistoryEntity;
import no.tidly.modules.organization.repository.DepartmentManagerHistoryRepository;
import no.tidly.modules.organization.repository.DepartmentRepository;

@Service
public class GetDepartmentManagerHistoryUseCase {

    private final DepartmentRepository departmentRepository;
    private final DepartmentManagerHistoryRepository historyRepository;

    public GetDepartmentManagerHistoryUseCase(DepartmentRepository departmentRepository,
            DepartmentManagerHistoryRepository historyRepository) {
        this.departmentRepository = departmentRepository;
        this.historyRepository = historyRepository;
    }

    public List<DepartmentManagerHistoryEntity> execute(UUID departmentId) {
        if (!this.departmentRepository.existsById(departmentId)) {
            throw new ResourceNotFoundException("Department not found");
        }
        // Assuming we want all history. JPA method needs to be added or we can rely on standard findAll and filter, 
        // but better to have a custom query in repo. 
        // For now, let's just add a method to repo to find by department id order by start date desc.
        return this.historyRepository.findAll().stream()
                .filter(h -> h.getDepartment().getId().equals(departmentId))
                .sorted((a, b) -> b.getStartDate().compareTo(a.getStartDate()))
                .toList();
    }
}
