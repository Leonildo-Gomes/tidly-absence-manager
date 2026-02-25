package no.tidly.modules.organization.mapper;

import org.springframework.stereotype.Component;

import no.tidly.modules.organization.domain.CompanyEntity;
import no.tidly.modules.organization.dto.CompanyResponse;

@Component
public class CompanyMapper {

    public CompanyResponse toResponse(CompanyEntity entity) {
        return new CompanyResponse(
                entity.getId(),
                entity.getName(),
                entity.getOrgNumber(),
                entity.getClerkOrgId(),
                entity.getIsActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }

}
