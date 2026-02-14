package no.tidly.modules.organization.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import no.tidly.modules.organization.domain.JobTitle;

@Repository
public interface JobTitleRepository extends JpaRepository<JobTitle, UUID> {
}
