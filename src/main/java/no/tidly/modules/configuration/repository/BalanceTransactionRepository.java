package no.tidly.modules.configuration.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import no.tidly.modules.configuration.domain.BalanceTransactionEntity;

@Repository
public interface BalanceTransactionRepository extends JpaRepository<BalanceTransactionEntity, UUID> {
    // List<BalanceTransactionEntity> findByEmployeeIdandYear(UUID employeeId,
    // Integer year);

    List<BalanceTransactionEntity> findByEmployeeId(UUID employeeId);
}
