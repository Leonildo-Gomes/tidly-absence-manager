package no.tidly.modules.workflow.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import no.tidly.modules.workflow.domain.AbsenceApprovalEntity;

@Repository
public interface AbsenceApprovalRepository extends JpaRepository<AbsenceApprovalEntity, UUID> {
    List<AbsenceApprovalEntity> findByAbsenceRequestId(UUID absenceRequestId);

    List<AbsenceApprovalEntity> findByApproverId(UUID approverId);
}
