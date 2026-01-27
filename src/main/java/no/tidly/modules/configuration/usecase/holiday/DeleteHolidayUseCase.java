package no.tidly.modules.configuration.usecase.holiday;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.modules.configuration.repository.HolidayRepository;

@Service
@RequiredArgsConstructor
public class DeleteHolidayUseCase {

    private final HolidayRepository repository;

    @Transactional
    public void execute(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Holiday not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
