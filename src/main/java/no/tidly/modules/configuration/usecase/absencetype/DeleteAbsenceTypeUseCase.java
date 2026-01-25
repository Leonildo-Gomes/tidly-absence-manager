package no.tidly.modules.configuration.usecase.absencetype;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.modules.configuration.repository.AbsenceTypeRepository;

@Service
@RequiredArgsConstructor
public class DeleteAbsenceTypeUseCase {

    private final AbsenceTypeRepository absenceTypeRepository;

    @Transactional
    public void execute(UUID id) {
        if (!absenceTypeRepository.existsById(id)) {
            throw new ResourceNotFoundException("AbsenceType not found with id: " + id);
        }
        absenceTypeRepository.deleteById(id);
    }
}
