package no.tidly.modules.configuration.domain;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import no.tidly.core.shared.BaseEntity;

@Entity
@Table(name = "absence_balances")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AbsenceBalanceEntity extends BaseEntity {

    @Column(name = "employee_id", nullable = false)
    private UUID employeeId;

    @Column(name = "absence_type_id", nullable = false)
    private UUID absenceTypeId;

    @Column(nullable = false)
    private Integer year;

    @Column(name = "total_entitled", nullable = false, precision = 5, scale = 2)
    private BigDecimal totalEntitled;

    @Column(name = "used_days", columnDefinition = "DECIMAL(5,2) DEFAULT 0")
    @Builder.Default
    private BigDecimal usedDays = BigDecimal.ZERO;

    @Column(name = "pending_days", columnDefinition = "DECIMAL(5,2) DEFAULT 0")
    @Builder.Default
    private BigDecimal pendingDays = BigDecimal.ZERO;

    @Column(name = "remaining_days", insertable = false, updatable = false)
    private BigDecimal remainingDays;
}
