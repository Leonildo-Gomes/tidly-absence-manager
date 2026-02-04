package no.tidly.modules.organization.usecases.department;

import static org.mockito.ArgumentMatchers.any;
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
    @DisplayName("Should throw exception when company is not found")
    void should_throw_exception_when_company_not_found() {
        var companyId = UUID.randomUUID();
        var departmentRequest = new DepartmentRequest("Department", "DEP", companyId, null);

        when(companyRepository.findById(companyId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            createDepartmentUseCase.execute(departmentRequest);
        });
    }

    @Test
    @DisplayName("Should throw exception when parent department is not found")
    void should_throw_exception_when_parent_department_not_found() {
        var companyId = UUID.randomUUID();
        var parentId = UUID.randomUUID();
        var departmentRequest = new DepartmentRequest("Department", "DEP", companyId, parentId);
        when(companyRepository.findById(companyId)).thenReturn(Optional.of(new CompanyEntity()));

        when(departmentRepository.findById(parentId)).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            createDepartmentUseCase.execute(departmentRequest);
        });
    }

    @Test
    @DisplayName("Should create department successfully")
    void should_create_department_successfully() {
        var companyId = UUID.randomUUID();
        var company = new CompanyEntity();
        company.setId(companyId);
        company.setName("Company");
        when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));
        DepartmentRequest departmentRequest = new DepartmentRequest("Department", "DEP", companyId, null);

        when(departmentRepository.save(any(DepartmentEntity.class))).thenAnswer(invocation -> {
            return invocation.getArgument(0);
        });
        var result = createDepartmentUseCase.execute(departmentRequest);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.name(), departmentRequest.name());
        Assertions.assertEquals(result.code(), departmentRequest.code());
        // Assertions.assertEquals(result.companyId(), departmentRequest.companyId());
        Assertions.assertNull(result.parentDepartmentId());
    }
}
