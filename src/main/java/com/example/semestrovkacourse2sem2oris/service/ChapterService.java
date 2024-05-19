package com.example.semestrovkacourse2sem2oris.service;

import com.example.semestrovkacourse2sem2oris.dto.request.ChapterRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.ChapterResponse;
import com.example.semestrovkacourse2sem2oris.model.ChapterEntity;

public interface ChapterService {

    String downloadText(ChapterEntity chapterEntity);

    void add(ChapterRequest request, String link);

    void delete(Long chapterId);

    ChapterResponse getByLink(String link);

    ChapterEntity getEntityByLink(String chapterLink);

    ChapterResponse put(String chapterLink, ChapterRequest chapterRequest);

    void deleteChapter(String chapterLink);
}
