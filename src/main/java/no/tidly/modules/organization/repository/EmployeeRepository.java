package no.tidly.modules.organization.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import no.tidly.modules.organization.domain.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {

}
