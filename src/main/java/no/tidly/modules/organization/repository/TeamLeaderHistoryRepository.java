package no.tidly.modules.organization.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import no.tidly.modules.organization.domain.TeamLeaderHistoryEntity;

@Repository
public interface TeamLeaderHistoryRepository extends JpaRepository<TeamLeaderHistoryEntity, UUID> {

    @Query("SELECT h FROM TeamLeaderHistoryEntity h WHERE h.team.id = :teamId AND h.endDate IS NULL")
    Optional<TeamLeaderHistoryEntity> findActiveByTeamId(UUID teamId);
}
