package com.example.semestrovkacourse2sem2oris.service;

import com.example.semestrovkacourse2sem2oris.dto.request.PostRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.PostResponse;
import com.example.semestrovkacourse2sem2oris.model.PostEntity;
import jakarta.servlet.http.HttpSession;

import javax.management.AttributeNotFoundException;

public interface PostService {

    void create(PostRequest request);

    PostResponse getByLink(String link);

    PostEntity getEntityByLink(String link);

    PostResponse create();

    PostResponse getPostFromSession(HttpSession session) throws AttributeNotFoundException;

    void saveChanges(PostRequest postRequest);

    PostResponse deleteChapter(String postLink, Integer chapterNumber);
}