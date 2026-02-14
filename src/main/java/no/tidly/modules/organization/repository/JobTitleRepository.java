package no.tidly.modules.organization.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import no.tidly.modules.organization.domain.JobTitleEntity;

@Repository
public interface JobTitleRepository extends JpaRepository<JobTitleEntity, UUID> {
}
