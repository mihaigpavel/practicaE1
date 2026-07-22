package ro.unibuc.fmi.mgp.e1.common;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), extractErrorMessage(ex), Instant.now());
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), Instant.now());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EntityReferencedException.class)
    public ErrorResponse handleEntityReferencedException(EntityReferencedException ex) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage(), Instant.now());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidDataException.class)
    public ErrorResponse handleInvalidDataException(InvalidDataException ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), Instant.now());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(PermissionDenied.class)
    public ErrorResponse handlePermisionDenied(PermissionDenied ex) {
        return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), Instant.now());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception ex) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), Instant.now());
    }


    private String extractErrorMessage(MethodArgumentNotValidException ex) {
        StringBuilder errorMsg = new StringBuilder();
        // Extragem toate erorile din excepție și populăm map-ul
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorMsg.append(fieldName).append(": ").append(errorMessage).append("; ");
        });
        return errorMsg.toString();
    }
}
