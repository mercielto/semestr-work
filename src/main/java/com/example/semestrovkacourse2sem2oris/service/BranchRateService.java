package com.example.semestrovkacourse2sem2oris.service;

import com.example.semestrovkacourse2sem2oris.dto.request.BranchRateRequest;

public interface BranchRateService {

    void rate(String link, BranchRateRequest request);

    Integer getCurrentUserRate(String branchLink);
}
