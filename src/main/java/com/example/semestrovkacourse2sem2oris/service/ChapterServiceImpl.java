package com.example.semestrovkacourse2sem2oris.service;

import com.example.semestrovkacourse2sem2oris.dto.request.ChapterRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.ChapterResponse;
import com.example.semestrovkacourse2sem2oris.exception.BranchNotFoundException;
import com.example.semestrovkacourse2sem2oris.exception.ChapterNotFoundException;
import com.example.semestrovkacourse2sem2oris.exception.CouldNotSaveChapterOnDisk;
import com.example.semestrovkacourse2sem2oris.exception.PostNotFoundException;
import com.example.semestrovkacourse2sem2oris.mapper.ChapterMapper;
import com.example.semestrovkacourse2sem2oris.model.BranchEntity;
import com.example.semestrovkacourse2sem2oris.model.ChapterEntity;
import com.example.semestrovkacourse2sem2oris.model.PostEntity;
import com.example.semestrovkacourse2sem2oris.model.UserEntity;
import com.example.semestrovkacourse2sem2oris.repository.BranchRepository;
import com.example.semestrovkacourse2sem2oris.repository.ChapterRepository;
import com.example.semestrovkacourse2sem2oris.util.CustomFileWorker;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChapterServiceImpl implements ChapterService {

    private final ChapterRepository chapterRepository;
    private final CustomFileWorker fileWorker;
    private final BranchRepository branchRepository;
    private final ChapterMapper mapper;

    // название пользователя, название поста, название ветки, название главы
    private final String fileStructure = "%s\\%s\\%s\\%s.txt";

    @Override
    public String downloadText(ChapterEntity chapterEntity) {
        String path = generateFilePath(chapterEntity);
        StringBuilder builder = fileWorker.read(path);

        return builder.toString();
    }

    public String generateFilePath(ChapterEntity chapter) {
        BranchEntity branchEntity = chapter.getBranch();
        UserEntity userEntity = branchEntity.getCreator();
        PostEntity postEntity = branchEntity.getPost();

        return fileStructure.formatted(userEntity.getLogin(), postEntity.getPostId(), branchEntity.getBranchId(),
                chapter.getLink());
    }

    @Transactional
    @Override
    public void add(ChapterRequest request, String branchLink) {
        BranchEntity branchEntity = branchRepository.findByLink(branchLink)
                .orElseThrow(() -> new BranchNotFoundException(branchLink));
        ChapterEntity chapterEntity = ChapterEntity.builder()
                .title(request.getTitle())
                .number(request.getNumber())
                .branch(branchEntity)
                .build();

        String fileName = UUID.randomUUID().toString();
        chapterEntity.setLink(fileName);
        saveFileOnDisk(chapterEntity, request.getText());
        chapterRepository.save(chapterEntity);
        chapterRepository.increaseNumber(chapterEntity.getId(), chapterEntity.getNumber());
    }

    private void saveFileOnDisk(ChapterEntity chapterEntity, String text) {
        String filePath = generateFilePath(chapterEntity);
        try {
            fileWorker.save(filePath, text);
        } catch (IOException e) {
            throw new CouldNotSaveChapterOnDisk(generateFilePath(chapterEntity));
        }
    }

    @Override
    public void delete(Long chapterId) {
        chapterRepository.deleteById(chapterId);
    }

    @Override
    public ChapterResponse getByLink(String link) {
        ChapterEntity entity = getEntityByLink(link);
        String text = downloadText(entity);
        ChapterResponse response = mapper.toResponse(entity);
        response.setText(text);
        return response;
    }

    @Override
    public ChapterEntity getEntityByLink(String link) {
        return chapterRepository.findByLink(link).orElseThrow(() -> new ChapterNotFoundException(link));
    }

    @Override
    public ChapterResponse put(String chapterLink, ChapterRequest chapterRequest) {
        ChapterEntity entity = getEntityByLink(chapterLink);
        entity.setNumber(chapterRequest.getNumber());
        entity.setTitle(chapterRequest.getTitle());
        saveFileOnDisk(entity, chapterRequest.getText());
        chapterRepository.save(entity);
        chapterRepository.increaseNumber(entity.getId(), entity.getNumber());
        return mapper.toResponse(entity);
    }

    @Transactional
    @Override
    public void deleteChapter(String chapterLink) {
        ChapterEntity chapter = getEntityByLink(chapterLink);
        // TODO: сделать удаление с понидением номера главы
    }
}
