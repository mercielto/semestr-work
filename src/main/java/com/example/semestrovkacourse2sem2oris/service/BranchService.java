package com.example.semestrovkacourse2sem2oris.service;

import com.example.semestrovkacourse2sem2oris.dto.request.BranchRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.BranchResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.BranchShortResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.BranchUserShortResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.ChapterResponse;
import com.example.semestrovkacourse2sem2oris.model.BranchEntity;
import com.example.semestrovkacourse2sem2oris.model.PostEntity;
import com.example.semestrovkacourse2sem2oris.model.SortType;

import java.util.List;
import java.util.Map;


public interface BranchService {

    Long create(BranchEntity entity);

    BranchEntity create(PostEntity postEntity);

    BranchEntity getEntityByLink(String branchLink);

    BranchResponse getMainBranchByPostLink(String link);

    BranchResponse getMain(List<BranchResponse> branches);

    BranchShortResponse getShortByLink(String link);

    void update(String link, BranchRequest request);

    List<BranchUserShortResponse> getPublishedUserShortByPostLinkWithPagination(String link,
                                                                                Integer from,
                                                                                Integer count,
                                                                                SortType sortType);

    BranchResponse createByBranchLink(String link, Integer number);

    Map<Integer, List<ChapterResponse>> getOrderedContentByBranchLink(String branchLink);

    BranchResponse getByLink(String link);
}
