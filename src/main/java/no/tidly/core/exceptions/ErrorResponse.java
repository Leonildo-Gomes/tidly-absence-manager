package no.tidly.core.exceptions;

import java.time.Instant;

public record ErrorResponse(String message, int status, Instant timestamp) {
}
