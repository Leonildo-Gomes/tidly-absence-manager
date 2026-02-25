package no.tidly.core.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class SecurityContextService {

    public Jwt getCurrentJwt() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication: " + authentication);
        if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
            System.out.println("jwt: " + authentication.getPrincipal());
            return (Jwt) authentication.getPrincipal();
        }
        throw new IllegalStateException("Nenhum token JWT encontrado no contexto");
    }

    public String getCurrentUserId() {
        return getCurrentJwt().getSubject(); // ID do utilizador (Clerk)
    }

    // Retorna a claim org_id do Clerk
    public String getCurrentOrganizationId() {
        return getCurrentJwt().getClaimAsString("org_id");
    }
}
