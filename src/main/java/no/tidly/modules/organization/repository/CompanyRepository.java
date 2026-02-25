package no.tidly.modules.organization.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import no.tidly.modules.organization.domain.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
    Optional<CompanyEntity> findByOrgNumber(String orgNumber);

    List<CompanyEntity> findAllByClerkOrgId(String clerkOrgId);
}
