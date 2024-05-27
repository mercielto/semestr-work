package com.example.semestrovkacourse2sem2oris.util;

import com.example.semestrovkacourse2sem2oris.service.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Aspect
@Component
@RequiredArgsConstructor
public class UserAspect {

    private final UserService userService;

    @Before("execution(* com.example.semestrovkacourse2sem2oris.controller.*.*(.., org.springframework.ui.Model))")
    public void addUserToModel(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof Model) {
                Model model = (Model) arg;
                model.addAttribute("navUser", userService.getCurrentUserResponse());
            }
        }
    }
}