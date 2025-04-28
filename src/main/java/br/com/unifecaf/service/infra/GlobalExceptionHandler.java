package br.com.unifecaf.service.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import br.com.unifecaf.service.dto.ApiError;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(
        MethodArgumentNotValidException ex, 
        WebRequest request
    ) {
        List<ApiError.FieldError> fieldErrors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(f -> new ApiError.FieldError(f.getField(), f.getDefaultMessage()))
            .collect(Collectors.toList());

        ApiError error = new ApiError(
            HttpStatus.BAD_REQUEST.value(),
            "Dados inválidos",
            request.getDescription(false)
        );
        error.setFieldErrors(fieldErrors);
        
        return ResponseEntity.badRequest().body(error);
    }

    // ===== 404 Not Found (Recursos inexistentes) =====
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleEntityNotFound(
        EntityNotFoundException ex, 
        WebRequest request
    ) {
        ApiError error = new ApiError(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            request.getDescription(false)
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // ===== 500 Internal Server Error (Erros inesperados) =====
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAllExceptions(
        Exception ex, 
        WebRequest request
    ) {
        ApiError error = new ApiError(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Ocorreu um erro interno no servidor",
            request.getDescription(false)
        );
        return ResponseEntity.internalServerError().body(error);
    }
}
