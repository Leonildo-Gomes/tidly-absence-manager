package no.tidly.core.exceptions;

public class OrgNumberFoundException extends RuntimeException {
    public OrgNumberFoundException(String orgNumber) {
        super("Organization number " + orgNumber + " already exists");
    }
}
