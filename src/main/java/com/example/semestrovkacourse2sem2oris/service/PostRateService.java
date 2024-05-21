package com.example.semestrovkacourse2sem2oris.service;

import com.example.semestrovkacourse2sem2oris.dto.request.PostRateRequest;

public interface PostRateService {

    Integer getCurrentUserRate(String link);

    void rate(String link, PostRateRequest request);
}
