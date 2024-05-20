package com.example.semestrovkacourse2sem2oris.util;

import com.example.semestrovkacourse2sem2oris.dto.request.UserRegistrationRequest;

public class UserParamsCheckerImpl implements UserParamsChecker{

    @Override
    public boolean check(UserRegistrationRequest request) {
        String email = request.getEmail();

        if (email == null) {
            return false;
        }

        if (!email.contains("@")) {
            return false;
        }

        String password = request.getPassword();

        if (password == null) {
            return false;
        }

        // TODO: проверить
//        if (!password.matches("(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}")) {
//            return false;
//        }

        return true;
    }
}
