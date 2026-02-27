package no.tidly.core.security;

import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class SecurityContextService {

    public Jwt getCurrentJwt() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
            return (Jwt) authentication.getPrincipal();
        }
        throw new IllegalStateException("Nenhum token JWT encontrado no contexto");
    }

    public String getCurrentUserId() {
        return getCurrentJwt().getSubject(); // ID do utilizador (Clerk)
    }

    /**
     * Retorna o ID da organização ativa do Clerk.
     * Suporta Session Token v2 (claim "o" com "id") e v1 (claim "org_id" no top-level).
     */
    public String getCurrentOrganizationId() {
        Jwt jwt = getCurrentJwt();
        // Clerk Session Token v2: organização está em "o" -> "id"
        Object oClaim = jwt.getClaim("o");
        if (oClaim instanceof Map<?, ?> oMap) {
            Object id = oMap.get("id");
            if (id instanceof String) {
                return (String) id;
            }
        }
        // Fallback: Clerk Session Token v1 (deprecado) usa "org_id" no top-level
        return jwt.getClaimAsString("org_id");
    }
}
