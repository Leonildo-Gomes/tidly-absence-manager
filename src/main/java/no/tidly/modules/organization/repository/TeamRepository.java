package no.tidly.modules.organization.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import no.tidly.modules.organization.domain.TeamEntity;

public interface TeamRepository extends JpaRepository<TeamEntity, UUID> {

    List<TeamEntity> findAllByDepartmentId(UUID departmentId);

    @Query("SELECT t FROM TeamEntity t JOIN t.department d WHERE d.company.id = :companyId")
    List<TeamEntity> findAllByCompanyId(@Param("companyId") UUID companyId);

}
