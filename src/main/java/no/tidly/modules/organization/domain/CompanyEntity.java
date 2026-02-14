package no.tidly.modules.organization.domain;

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
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
public class CompanyEntity extends BaseEntity {

    @Column(nullable = false, length = 150)
    private String name;

    @Column(name = "org_number", length = 20, unique = true, nullable = false)
    private String orgNumber;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
    @Column(name = "clerk_org_id", unique = true, nullable = false)
    private String clerkOrgId;
}
