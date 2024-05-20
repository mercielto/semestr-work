package com.example.semestrovkacourse2sem2oris.service;

import com.example.semestrovkacourse2sem2oris.dto.request.BranchCommentRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.BranchCommentResponse;

import java.util.List;

public interface BranchCommentService {

    List<BranchCommentResponse> getByBranchLinkWithPagination(String link, int from, int count);

    BranchCommentResponse createByBranchLink(String link, BranchCommentRequest request);
}
