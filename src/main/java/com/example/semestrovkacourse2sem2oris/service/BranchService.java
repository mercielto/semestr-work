package com.example.semestrovkacourse2sem2oris.service;

import com.example.semestrovkacourse2sem2oris.dto.response.BranchResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.BranchShortResponse;
import com.example.semestrovkacourse2sem2oris.model.BranchEntity;
import com.example.semestrovkacourse2sem2oris.model.PostEntity;

import java.util.List;


public interface BranchService {

    Long create(BranchEntity entity);

    BranchEntity create(PostEntity postEntity);

    BranchEntity getEntityByLink(String branchLink);

    BranchResponse getByPostLink(String link);

    BranchResponse getMain(List<BranchResponse> branches);

    BranchShortResponse getShortByLink(String link);
}
