package com.example.semestrovkacourse2sem2oris.configuration;

import com.example.semestrovkacourse2sem2oris.annotation.RestExceptionAnnotation;
import com.example.semestrovkacourse2sem2oris.exception.ServiceException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = RestExceptionAnnotation.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public String handleServiceException(ServiceException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("status", ex.getStatus().value());
        return "normal/error";
    }
}