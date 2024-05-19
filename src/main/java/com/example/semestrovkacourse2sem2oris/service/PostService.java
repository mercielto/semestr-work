package com.example.semestrovkacourse2sem2oris.service;

import com.example.semestrovkacourse2sem2oris.dto.request.PostRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.PostResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.PostShortResponse;
import com.example.semestrovkacourse2sem2oris.model.PostEntity;
import jakarta.servlet.http.HttpSession;

import javax.management.AttributeNotFoundException;

public interface PostService {

    void create(PostRequest request);

    PostResponse getByLink(String link);

    PostEntity getEntityByLink(String link);

    PostResponse create();

    PostResponse getPostFromSession(HttpSession session) throws AttributeNotFoundException;

    void saveChanges(PostRequest postRequest, String link);

    PostResponse deleteChapter(String postLink, Integer chapterNumber);

    PostShortResponse getByChapterLink(String chapterLink);

    PostShortResponse getShortByLink(String link);

    void publish(PostRequest request, String link);
}
