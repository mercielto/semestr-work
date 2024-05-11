package com.example.semestrovkacourse2sem2oris.service;

import com.example.semestrovkacourse2sem2oris.dto.request.ChapterRequest;
import com.example.semestrovkacourse2sem2oris.exception.CouldNotSaveChapterOnDisk;
import com.example.semestrovkacourse2sem2oris.mapper.ChapterMapper;
import com.example.semestrovkacourse2sem2oris.model.ChapterEntity;
import com.example.semestrovkacourse2sem2oris.model.PostEntity;
import com.example.semestrovkacourse2sem2oris.model.UserEntity;
import com.example.semestrovkacourse2sem2oris.repository.ChapterRespository;
import com.example.semestrovkacourse2sem2oris.util.CustomFileWorker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ChapterServiceImpl implements ChapterService {

    private final ChapterRespository repository;
    private final CustomFileWorker fileWorker;
    private final PostService postService;
    private final ChapterMapper mapper;

    @Override
    public String downloadText(ChapterEntity chapterEntity) {
        PostEntity postEntity = chapterEntity.getPost();
        UserEntity userEntity = postEntity.getCreator();

        // TODO: подумать, как организовать файлы на диске
        String path = "%s/%s/%s".formatted(userEntity.getLogin(), postEntity.getPostId(),
                chapterEntity.getId());

        StringBuilder builder = fileWorker.read(path);

        return builder.toString();
    }

    public String generateFilePath(PostEntity post, ChapterEntity chapter) {
        UserEntity userEntity = post.getCreator();
        return "/%s/%s/%s.txt".formatted(userEntity.getLogin(), post.getPostId(), chapter.getId());
    }

    @Override
    public void add(ChapterRequest request, String link) {
        PostEntity postEntity = postService.getEntityByLink(link);
        ChapterEntity chapterEntity = mapper.toEntity(request);
        chapterEntity.setPost(postEntity);
        chapterEntity = repository.save(chapterEntity);

        String filePath = generateFilePath(postEntity, chapterEntity);
        try {
            fileWorker.save(filePath, request.getText());
            chapterEntity.setFilePath(filePath);
            repository.save(chapterEntity);
        } catch (IOException e) {
            throw new CouldNotSaveChapterOnDisk(postEntity.getPostId(), chapterEntity.getId(), filePath);
        }
    }

    @Override
    public void delete(Long chapterId) {
        repository.deleteById(chapterId);
    }
}
