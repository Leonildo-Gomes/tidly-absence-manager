package no.tidly.modules.organization.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import no.tidly.modules.organization.domain.TeamEntity;

public interface TeamRepository extends JpaRepository<TeamEntity, UUID> {

}
