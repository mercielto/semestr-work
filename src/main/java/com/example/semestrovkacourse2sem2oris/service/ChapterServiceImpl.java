package com.example.semestrovkacourse2sem2oris.service;

import com.example.semestrovkacourse2sem2oris.dto.request.BranchRateRequest;
import com.example.semestrovkacourse2sem2oris.dto.request.ChapterRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.ChapterResponse;
import com.example.semestrovkacourse2sem2oris.exception.BranchNotFoundException;
import com.example.semestrovkacourse2sem2oris.exception.ChapterNotFoundException;
import com.example.semestrovkacourse2sem2oris.exception.CouldNotSaveChapterOnDisk;
import com.example.semestrovkacourse2sem2oris.mapper.ChapterMapper;
import com.example.semestrovkacourse2sem2oris.model.BranchEntity;
import com.example.semestrovkacourse2sem2oris.model.ChapterEntity;
import com.example.semestrovkacourse2sem2oris.model.PostEntity;
import com.example.semestrovkacourse2sem2oris.model.UserEntity;
import com.example.semestrovkacourse2sem2oris.repository.BranchRateRepository;
import com.example.semestrovkacourse2sem2oris.repository.BranchRepository;
import com.example.semestrovkacourse2sem2oris.repository.ChapterRepository;
import com.example.semestrovkacourse2sem2oris.util.CustomFileWorker;
import com.example.semestrovkacourse2sem2oris.util.LinkGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChapterServiceImpl implements ChapterService {

    private final ChapterRepository chapterRepository;
    private final CustomFileWorker fileWorker;
    private final BranchRepository branchRepository;
    private final ChapterMapper mapper;
    private final LinkGenerator linkGenerator;

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
        chapterEntity = chapterRepository.save(chapterEntity);
        branchEntity.getChapters().add(chapterEntity);
        branchRepository.save(branchEntity);
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
    public String put(String chapterLink, ChapterRequest chapterRequest) {
        ChapterEntity entity = getEntityByLink(chapterLink);
        entity.setNumber(chapterRequest.getNumber());
        entity.setTitle(chapterRequest.getTitle());
        saveFileOnDisk(entity, chapterRequest.getText());
        chapterRepository.save(entity);
        chapterRepository.increaseNumber(entity.getId(), entity.getNumber());
        return entity.getBranch().getPost().getWebLink();
    }

    @Transactional
    @Override
    public void deleteChapter(String chapterLink) {
        ChapterEntity chapter = getEntityByLink(chapterLink);
        BranchEntity branchEntity = chapter.getBranch();
        chapterRepository.decreaseNumber(chapter.getId(), chapter.getNumber());
        branchEntity.getChapters().remove(chapter);
        branchRepository.save(branchEntity);
        chapterRepository.delete(chapter);
    }

    @Override
    public ChapterEntity create(BranchEntity branch) {
        Optional<ChapterEntity> lastChapter = branch.getChapters().stream()
                .max(Comparator.comparing(ChapterEntity::getNumber));
        ChapterEntity chapter = ChapterEntity.builder()
                .branch(branch)
                .number(lastChapter.map(entity -> entity.getNumber() + 1).orElse(1))
                .title("Default")
                .link(linkGenerator.generateLink())
                .build();
        chapterRepository.save(chapter);
        return chapter;
    }
}
