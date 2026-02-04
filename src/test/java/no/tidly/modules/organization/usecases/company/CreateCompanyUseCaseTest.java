package no.tidly.modules.organization.usecases.company;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import no.tidly.modules.organization.repository.CompanyRepository;
import no.tidly.modules.organization.usecase.company.CreateCompanyUseCase;

@ExtendWith(MockitoExtension.class)
public class CreateCompanyUseCaseTest {

    @InjectMocks
    private CreateCompanyUseCase createCompanyUseCase;

    @Mock
    private CompanyRepository companyRepository;

    @Test
    void createCompany() {

    }
}
