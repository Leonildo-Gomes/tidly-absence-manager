package no.tidly.modules.organization.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
public record UpdateCompanyRequest(
    @NotBlank 
    @Size(max = 150) 
    String name,
    
    @Size(max = 20) 
    String organizationNumber,
    
    @NotNull 
    Boolean isActive // Adicionado para suportar o Toggle do Frontend
) {}