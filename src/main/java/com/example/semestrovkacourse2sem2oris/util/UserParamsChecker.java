package com.example.semestrovkacourse2sem2oris.util;

import com.example.semestrovkacourse2sem2oris.dto.request.UserRegistrationRequest;

public interface UserParamsChecker {

    boolean check(UserRegistrationRequest request);
}
