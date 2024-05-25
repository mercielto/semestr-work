package com.example.semestrovkacourse2sem2oris.service;

import com.example.semestrovkacourse2sem2oris.dto.request.PostReadRequest;
import com.example.semestrovkacourse2sem2oris.dto.request.PostRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.ChapterResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.PostResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.PostShortResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.PostUserShortResponse;
import com.example.semestrovkacourse2sem2oris.model.PostEntity;
import com.example.semestrovkacourse2sem2oris.model.PostReadStatus;
import jakarta.servlet.http.HttpSession;

import javax.management.AttributeNotFoundException;
import java.util.List;
import java.util.Map;

public interface PostService {

    void create(PostRequest request);

    PostResponse getByLink(String link);

    PostEntity getEntityByLink(String link);

    PostResponse create();

    PostResponse getPostFromSession(HttpSession session) throws AttributeNotFoundException;

    void saveChanges(PostRequest postRequest, String link);

    PostShortResponse getByChapterLink(String chapterLink);

    PostShortResponse getShortByLink(String link);

    void publish(PostRequest request, String link);

    PostShortResponse getShortByBranchLink(String branchLink);

    void delete(String link);

    void changeReadStatus(String link, PostReadRequest request);

    PostReadStatus isCurrentUserRead(PostEntity entity);

    PostUserShortResponse getUserShortByLink(String link);

    Map<Integer, List<ChapterResponse>> getOrderedContentByPostLinkAndBranchLink(String postLink, String branchLink);
}
