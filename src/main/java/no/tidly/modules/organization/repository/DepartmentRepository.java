package no.tidly.modules.organization.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import no.tidly.modules.organization.domain.DepartmentEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, UUID> {

}
