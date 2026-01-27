package no.tidly.modules.configuration.domain;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import no.tidly.core.shared.BaseEntity;

@Entity
@Table(name = "company_absence_settings")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CompanyAbsenceSettingsEntity extends BaseEntity {

    @Column(name = "company_id", nullable = false)
    private UUID companyId;

    @Column(name = "absence_type_id", nullable = false)
    private UUID absenceTypeId;

    @Column(name = "department_id")
    private UUID departmentId;

    @Column(name = "max_days_per_year", nullable = false, precision = 5, scale = 2)
    private BigDecimal maxDaysPerYear;

    @Column(name = "min_notice_days", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer minNoticeDays;
}
