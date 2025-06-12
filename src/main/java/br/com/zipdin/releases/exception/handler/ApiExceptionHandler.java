package br.com.zipdin.releases.exception.handler;

import br.com.zipdin.releases.exception.ReleaseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler
{
    @ExceptionHandler(ReleaseNotFoundException.class)
    public ResponseEntity<ApiError> ReleaseNotFoundException(ReleaseNotFoundException releaseNotFoundException)
    {
        ApiError apiError = new ApiError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), releaseNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }
}
