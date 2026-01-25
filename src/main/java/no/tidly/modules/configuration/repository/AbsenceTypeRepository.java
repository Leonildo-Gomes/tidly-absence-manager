package no.tidly.modules.configuration.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import no.tidly.modules.configuration.domain.AbsenceTypeEntity;

@Repository
public interface AbsenceTypeRepository extends JpaRepository<AbsenceTypeEntity, UUID> {
}
