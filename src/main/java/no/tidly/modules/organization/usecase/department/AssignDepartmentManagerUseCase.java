package no.tidly.modules.organization.usecase.department;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.modules.organization.domain.DepartmentManagerHistoryEntity;
import no.tidly.modules.organization.repository.DepartmentManagerHistoryRepository;
import no.tidly.modules.organization.repository.DepartmentRepository;
import no.tidly.modules.organization.repository.EmployeeRepository;

@Service
public class AssignDepartmentManagerUseCase {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentManagerHistoryRepository historyRepository;

    public AssignDepartmentManagerUseCase(DepartmentRepository departmentRepository,
            EmployeeRepository employeeRepository,
            DepartmentManagerHistoryRepository historyRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.historyRepository = historyRepository;
    }

    @Transactional
    public void execute(UUID departmentId, UUID managerId) {
        var department = this.departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        var manager = this.employeeRepository.findById(managerId)
                .orElseThrow(() -> new ResourceNotFoundException("Manager (Employee) not found"));

        // 1. Close current active manager tenure if exists
        this.historyRepository.findActiveByDepartmentId(departmentId)
                .ifPresent(currentHistory -> {
                    // If the same manager is already assigned and active, do nothing
                    if (currentHistory.getManager().getId().equals(managerId)) {
                        return;
                    }
                    currentHistory.setEndDate(LocalDate.now());
                    this.historyRepository.save(currentHistory);
                });

        // 2. Create new history record
        // Verify if we didn't just return above (same manager check is tricky inside ifPresent,
        // but let's assume if we are here we need to create a new one. 
        // Actually the check above returns void lambda, so execution continues.
        // We should add a guard clause.
        
        boolean alreadyAssigned = this.historyRepository.findActiveByDepartmentId(departmentId)
             .map(h -> h.getManager().getId().equals(managerId) && h.getEndDate() == null)
             .orElse(false);

        if (alreadyAssigned) {
             return;
        }

        var newHistory = DepartmentManagerHistoryEntity.builder()
                .department(department)
                .manager(manager)
                .startDate(LocalDate.now())
                .build();

        this.historyRepository.save(newHistory);
    }
}
