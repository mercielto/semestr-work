package com.example.semestrovkacourse2sem2oris.exception;

import org.springframework.http.HttpStatus;

public class CouldNotSaveChapterOnDisk extends ServiceException {
    public CouldNotSaveChapterOnDisk(Long postId, Long chapterEntityId, String path) {
        super("Could not save chapter (%s) post (%s) by path '%s'".formatted(postId, chapterEntityId, path),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
