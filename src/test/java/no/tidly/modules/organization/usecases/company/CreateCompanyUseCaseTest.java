package no.tidly.modules.organization.usecases.company;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import no.tidly.core.exceptions.OrgNumberFoundException;
import no.tidly.modules.organization.domain.CompanyEntity;
import no.tidly.modules.organization.dto.CompanyRequest;
import no.tidly.modules.organization.repository.CompanyRepository;
import no.tidly.modules.organization.usecase.company.CreateCompanyUseCase;

@ExtendWith(MockitoExtension.class)
public class CreateCompanyUseCaseTest {

    @InjectMocks
    private CreateCompanyUseCase createCompanyUseCase;

    @Mock
    private CompanyRepository companyRepository;

    @Test
    void should_not_to_able_to_create_company_when_name_is_null() {
        CompanyRequest companyRequest = new CompanyRequest(null, "123456789", "123456789");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            createCompanyUseCase.execute(companyRequest);
        });
    }

    @Test
    void should_not_to_able_to_create_company_when_organization_number_is_null() {
        CompanyRequest companyRequest = new CompanyRequest("Company", null, "123456789");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            createCompanyUseCase.execute(companyRequest);
        });
    }

    @Test
    void should_be_able_to_create_company() {
        CompanyRequest companyRequest = new CompanyRequest("Company", "123456789", "123456789");
        createCompanyUseCase.execute(companyRequest);
    }

    @Test
    void should_not_to_be_able_to_create_company_when_organization_number_exists() {
        CompanyRequest companyRequest = new CompanyRequest("Company", "123456789", "123456789");
        when(companyRepository.findByOrgNumber(companyRequest.organizationNumber()))
                .thenReturn(Optional.of(new CompanyEntity()));
        Assertions.assertThrows(OrgNumberFoundException.class, () -> {
            createCompanyUseCase.execute(companyRequest);
        });
    }
}
