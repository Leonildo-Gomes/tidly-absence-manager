package no.tidly.modules.billing.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import no.tidly.modules.billing.domain.SubscriptionInvoice;

@Repository
public interface SubscriptionInvoiceRepository extends JpaRepository<SubscriptionInvoice, UUID> {
    List<SubscriptionInvoice> findBySubscriptionId(UUID subscriptionId);
}
