package com.jalasoft.bootcamp.becommon.domain.exceptions;

import com.jalasoft.bootcamp.becommon.domain.exceptions.BaseException;
import com.jalasoft.bootcamp.becommon.domain.exceptions.Errors;
import com.jalasoft.bootcamp.becommon.domain.exceptions.InvalidArgumentsEntityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DomainExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Map<String, Object>> handleBaseException(BaseException exception)
    {
        return ResponseEntity.status(exception.getStatusCode())
            .body(exceptionBodyBuilder(exception));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex,
        HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        List<Errors> errorsList = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors())
        {
            errorsList.add(new Errors(
                error.getField(),
                error.getField() + ": " + error.getDefaultMessage()));
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors())
        {
            errorsList.add(new Errors(
                error.getObjectName(),
                error.getObjectName() + ": " + error.getDefaultMessage()));
        }
        InvalidArgumentsEntityException exception =
            new InvalidArgumentsEntityException(errorsList);
        return ResponseEntity.status(exception.getStatusCode())
            .body(exceptionBodyBuilder(exception));
    }

    private Map<String, Object> exceptionBodyBuilder(BaseException baseException)
    {
        Map<String, Object> body = new HashMap<>();
        body.put("statusCode", baseException.getStatusCode());
        body.put("errorMessage", baseException.getErrorMessage());
        body.put("details", baseException.getDetails());
        return body;
    }
}
