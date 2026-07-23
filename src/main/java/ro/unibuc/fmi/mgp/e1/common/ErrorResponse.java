package ro.unibuc.fmi.mgp.e1.common;

import java.time.Instant;


public record ErrorResponse(int status, String message, Instant timestamp) {
}
