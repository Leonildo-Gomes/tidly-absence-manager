package no.tidly.modules.billing.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import no.tidly.modules.billing.domain.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
    Optional<Subscription> findByCompanyId(UUID companyId);

    @Query("SELECT s FROM Subscription s WHERE s.companyId = :companyId AND s.status IN ('ACTIVE', 'TRIAL')")
    Optional<Subscription> findActiveByCompanyId(@Param("companyId") UUID companyId);
}
