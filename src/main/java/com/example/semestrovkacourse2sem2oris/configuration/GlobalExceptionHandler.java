package com.example.semestrovkacourse2sem2oris.configuration;

import com.example.semestrovkacourse2sem2oris.annotation.NotRestExceptionAnnotation;
import com.example.semestrovkacourse2sem2oris.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(annotations = NotRestExceptionAnnotation.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public String handleServiceException(ServiceException ex, Model model) {
        log.error("Error %s arised with message '%s' and http status '%s'".formatted(ex.getClass().getName(),
                ex.getMessage(), ex.getStatus()));
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("status", ex.getStatus().value());
        return "normal/error";
    }
}