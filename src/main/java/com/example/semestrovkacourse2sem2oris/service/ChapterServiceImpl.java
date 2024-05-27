package com.example.semestrovkacourse2sem2oris.service;

import com.example.semestrovkacourse2sem2oris.dto.request.ChapterRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.ChapterResponse;
import com.example.semestrovkacourse2sem2oris.exception.*;
import com.example.semestrovkacourse2sem2oris.mapper.ChapterMapper;
import com.example.semestrovkacourse2sem2oris.model.BranchEntity;
import com.example.semestrovkacourse2sem2oris.model.ChapterEntity;
import com.example.semestrovkacourse2sem2oris.model.PostEntity;
import com.example.semestrovkacourse2sem2oris.model.UserEntity;
import com.example.semestrovkacourse2sem2oris.repository.BranchRepository;
import com.example.semestrovkacourse2sem2oris.repository.ChapterRepository;
import com.example.semestrovkacourse2sem2oris.util.CustomFileWorker;
import com.example.semestrovkacourse2sem2oris.util.LinkGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

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
        chapterRepository.increaseNumber(chapterEntity.getId(), chapterEntity.getNumber(), branchEntity);
    }

    private void saveFileOnDisk(ChapterEntity chapterEntity, String text) {
        String filePath = generateFilePath(chapterEntity);
        try {
            fileWorker.save(filePath, text);
        } catch (IOException e) {
            throw new CouldNotSaveChapterOnDiskException(generateFilePath(chapterEntity));
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
        chapterRepository.increaseNumber(entity.getId(), entity.getNumber(), entity.getBranch());
        return entity.getBranch().getPost().getWebLink();
    }

    @Transactional
    @Override
    public void deleteChapter(String chapterLink) {
        ChapterEntity chapter = getEntityByLink(chapterLink);
        BranchEntity branchEntity = chapter.getBranch();
        chapterRepository.decreaseNumber(chapter.getId(), chapter.getNumber(), branchEntity);
        branchEntity.getChapters().remove(chapter);

        if (chapterRepository.doesItHasBranchings(branchEntity, chapter.getNumber())) {
            throw new CanNotDeleteChapter("Chapter has branchings");
        }

        if (branchEntity.getChapters().size() == 0) {
            branchRepository.delete(branchEntity);
        } else {
            branchRepository.save(branchEntity);
        }

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

    @Override
    public ChapterEntity create(BranchEntity branch, Integer number) {
        ChapterEntity chapter = ChapterEntity.builder()
                .branch(branch)
                .number(number)
                .title("Default")
                .link(linkGenerator.generateLink())
                .build();
        chapterRepository.save(chapter);
        return chapter;
    }

    @Override
    public ChapterEntity getFirstChapter(BranchEntity branch) {
        return chapterRepository.findFirstByBranchOrderByNumberAsc(branch).orElseThrow(
                () -> new ChapterNotFoundException("Branch link: <%s>".formatted(branch.getLink()))
        );
    }

    @Override
    public void addChaptersFromOneEnd(Map<Integer, List<ChapterResponse>> content, BranchEntity branch) {
        // добавляем главы ветки
        for (ChapterEntity chapter : branch.getChapters()) {
            List<ChapterResponse> chapters = new ArrayList<>();
            chapters.add(mapper.toResponse(chapter));
            content.put(chapter.getNumber(), chapters);
        }

        // добавляем ветвления
        for (BranchEntity descendant : branch.getDescendants()) {
            ChapterEntity additionalChapter = getFirstChapter(descendant);
            content.get(additionalChapter.getNumber()).add(mapper.toResponse(additionalChapter));
        }
    }


    // рекурсия идет с хвоста
    @Override
    public void getAllChaptersRecursively(Map<Integer, List<ChapterResponse>> content, BranchEntity branch) {

        if (content.size() == 0) {
            addChaptersFromOneEnd(content, branch);
        } else {

            // добавляем от начала ветки до первого значения в content
            ChapterEntity firstChapter = getFirstChapter(branch);
            Integer start = firstChapter.getNumber();
            Integer end = content.keySet().stream().min(Integer::compareTo).orElseThrow(
                    () -> new NotFoundServiceException("Min value of key set not found to fill content Map"));

            List<ChapterEntity> branchChapters = branch.getChapters();
            for (int i = start; i < end; i++) {
                ChapterEntity chapter = branchChapters.get(i - start);

                List<ChapterResponse> chapters = new ArrayList<>();
                chapters.add(mapper.toResponse(chapter));

                content.put(chapter.getNumber(), chapters);
            }

            // end уже должен содержаться в content
            ChapterEntity chapter = branchChapters.get(end - start);
            content.get(end).add(mapper.toResponse(chapter));

            // добавляем ветвления (включаем в диапазон end, чтобы можно было увидеть другие ветвления с этой главы)
            for (BranchEntity descendant : branch.getDescendants()) {
                ChapterEntity additionalChapter = getFirstChapter(descendant);
                Integer additionalChapterNumber = additionalChapter.getNumber();

                if (additionalChapterNumber >= start && additionalChapterNumber <= end) {
                    ChapterResponse response = mapper.toResponse(additionalChapter);
                    List<ChapterResponse> chapterResponses = content.get(additionalChapter.getNumber());

                    if (!chapterResponses.contains(response)) {
                        chapterResponses.add(response);
                    }
                }
            }
        }

        BranchEntity parentBranch = branch.getParentBranch();
        if (parentBranch == null) {
            return;
        }

        getAllChaptersRecursively(content, parentBranch);
    }

    @Override
    public ChapterResponse getLastChapterByBranchLink(String branchLink) {
        BranchEntity branch = branchRepository.findByLink(branchLink)
                .orElseThrow(() -> new BranchNotFoundException(branchLink));
        return mapper.toResponse(chapterRepository.findFirstByBranchOrderByNumberDesc(branch)
                .orElseThrow(() -> new ChapterNotFoundException("last chapter of branch %s".formatted(branchLink))));
    }

    @Override
    public ChapterResponse getFirstChapterByBranchLink(String branchLink) {
        BranchEntity branch = branchRepository.findByLink(branchLink)
                .orElseThrow(() -> new BranchNotFoundException(branchLink));
        return mapper.toResponse(chapterRepository.findFirstByBranchOrderByNumberAsc(branch)
                .orElseThrow(() -> new ChapterNotFoundException("first chapter of branch %s".formatted(branchLink))));
    }
}
