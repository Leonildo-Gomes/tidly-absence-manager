package no.tidly.modules.billing.domain;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import no.tidly.core.shared.BaseEntity;
import no.tidly.modules.billing.domain.enums.PlanInterval;

@Entity
@Table(name = "plans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Plan extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(length = 3, nullable = false)
    @Builder.Default
    private String currency = "NOK";

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlanInterval interval;

    @Column(name = "max_employees")
    private Integer maxEmployees;

    @Column(columnDefinition = "jsonb")
    private String features;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

}
