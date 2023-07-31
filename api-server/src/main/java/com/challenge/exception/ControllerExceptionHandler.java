package com.challenge.exception;

import com.challenge.pattern.StandardError;
import com.challenge.pattern.ValidationError;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandardError> authorizationException(AuthorizationException e) {
        StandardError err = new StandardError(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
    }

    @ExceptionHandler(TooManyRequestsException.class)
    public ResponseEntity<StandardError> manyRequestsException(TooManyRequestsException e) {
        StandardError err = new StandardError(HttpStatus.TOO_MANY_REQUESTS.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(err);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<StandardError> businessException(BusinessException e) {
    	StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
    
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e) {
        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e) {
        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpEntity<ValidationError> validation(MethodArgumentNotValidException e) {
        ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(),
                "request invalido! Campos obrigatorios estão em branco ou faltando!", System.currentTimeMillis());
        for (FieldError x : e.getBindingResult().getFieldErrors()) {
            err.addError(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}