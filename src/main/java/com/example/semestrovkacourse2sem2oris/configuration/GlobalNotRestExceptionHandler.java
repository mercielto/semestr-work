package com.example.semestrovkacourse2sem2oris.configuration;

import com.example.semestrovkacourse2sem2oris.annotation.RestExceptionAnnotation;
import com.example.semestrovkacourse2sem2oris.dto.response.Response;
import com.example.semestrovkacourse2sem2oris.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(annotations = RestExceptionAnnotation.class)
public class GlobalNotRestExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Response> handleServiceException(ServiceException ex) {
        log.error("Error %s arised with message '%s' and http status '%s'".formatted(ex.getClass().getName(),
                ex.getMessage(), ex.getStatus()));
        return new ResponseEntity<>(new Response(ex.getMessage()), ex.getStatus());
    }
}