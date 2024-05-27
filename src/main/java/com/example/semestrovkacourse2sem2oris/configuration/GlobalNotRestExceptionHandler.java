package com.example.semestrovkacourse2sem2oris.configuration;

import com.example.semestrovkacourse2sem2oris.annotation.NotRestExceptionAnnotation;
import com.example.semestrovkacourse2sem2oris.dto.response.Response;
import com.example.semestrovkacourse2sem2oris.exception.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = NotRestExceptionAnnotation.class)
public class GlobalNotRestExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Response> handleServiceException(ServiceException ex) {
        return new ResponseEntity<>(new Response(ex.getMessage()), ex.getStatus());
    }
}