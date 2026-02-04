package no.tidly.modules.organization.usecases.department;

import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import no.tidly.core.exceptions.ResourceNotFoundException;
import no.tidly.modules.organization.domain.CompanyEntity;
import no.tidly.modules.organization.domain.DepartmentEntity;
import no.tidly.modules.organization.dto.DepartmentRequest;
import no.tidly.modules.organization.repository.CompanyRepository;
import no.tidly.modules.organization.repository.DepartmentRepository;
import no.tidly.modules.organization.usecase.department.CreateDepartmentUseCase;

@ExtendWith(MockitoExtension.class)
public class CreateDepartmentUseCaseTest {

    @InjectMocks
    private CreateDepartmentUseCase createDepartmentUseCase;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Test
    @DisplayName("Should not be able to create department when company not found")
    void should_not_to_be_able_to_create_department_when_company_not_found() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            DepartmentRequest departmentRequest = new DepartmentRequest(null, null, null, null);
            createDepartmentUseCase.execute(departmentRequest);
        });
    }

    @Test
    @DisplayName("Should not be able to create department when parent department not found")
    void should_not_to_be_able_to_create_department_when_parent_department_not_found() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            DepartmentRequest departmentRequest = new DepartmentRequest(null, null, null, null);
            createDepartmentUseCase.execute(departmentRequest);
        });
    }

    @Test
    @DisplayName("Should be able to create department")
    void should_be_able_to_create_department_with_parent_department_is_null() {
        var companyId = UUID.randomUUID();
        when(companyRepository.findById(companyId)).thenReturn(Optional.of(new CompanyEntity()));
        DepartmentRequest departmentRequest = new DepartmentRequest("Department", "DEP", companyId, null);
        createDepartmentUseCase.execute(departmentRequest);
    }

    @Test
    @DisplayName("Should be able to create department")
    void should_be_able_to_create_department_with_parent_department_is_not_null() {
        var companyId = UUID.randomUUID();
        when(companyRepository.findById(companyId)).thenReturn(Optional.of(new CompanyEntity()));
        var parentDepartmentId = UUID.randomUUID();
        when(departmentRepository.findById(parentDepartmentId)).thenReturn(Optional.of(new DepartmentEntity()));
        DepartmentRequest departmentRequest = new DepartmentRequest("Department", "DEP", companyId, parentDepartmentId);
        createDepartmentUseCase.execute(departmentRequest);
    }
}
