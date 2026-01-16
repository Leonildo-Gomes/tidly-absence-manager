package no.tidly.modules.organization.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import no.tidly.modules.organization.domain.DepartmentManagerHistoryEntity;

public interface DepartmentManagerHistoryRepository extends JpaRepository<DepartmentManagerHistoryEntity, UUID> {

}
