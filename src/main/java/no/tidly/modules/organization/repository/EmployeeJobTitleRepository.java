package no.tidly.modules.organization.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import no.tidly.modules.organization.domain.EmployeeJobTitle;

@Repository
public interface EmployeeJobTitleRepository extends JpaRepository<EmployeeJobTitle, UUID> {
    List<EmployeeJobTitle> findByEmployeeId(UUID employeeId);
}
