package com.example.semestrovkacourse2sem2oris.service;

import com.example.semestrovkacourse2sem2oris.dto.request.ChapterRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.ChapterResponse;
import com.example.semestrovkacourse2sem2oris.exception.BranchNotFoundException;
import com.example.semestrovkacourse2sem2oris.model.BranchEntity;
import com.example.semestrovkacourse2sem2oris.model.ChapterEntity;
import com.example.semestrovkacourse2sem2oris.repository.BranchRepository;
import com.example.semestrovkacourse2sem2oris.repository.ChapterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
public class ChapterServiceTest {

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private ChapterRepository chapterRepository;

    private BranchEntity branch;

    @BeforeEach
    public void setUp() {
        branch = new BranchEntity();
        branch.setLink("testBranch");
        branchRepository.save(branch);
    }

    @Test
    @Transactional
    public void testCreateChapter() {
        ChapterRequest request = new ChapterRequest();
        request.setTitle("New Chapter");
        request.setNumber(1);
        request.setText("Chapter text");

        Long chapterId = chapterService.create(request);

        Optional<ChapterEntity> createdChapter = chapterRepository.findById(chapterId);
        assertThat(createdChapter).isPresent();
        assertThat(createdChapter.get().getTitle()).isEqualTo("New Chapter");
    }

    @Test
    @Transactional
    public void testDeleteChapter() {
        ChapterEntity chapter = new ChapterEntity();
        chapter.setLink("testLink");
        chapter.setTitle("Test Chapter");
        chapter.setBranch(branch);
        chapterRepository.save(chapter);

        chapterService.deleteChapter("testLink");

        Optional<ChapterEntity> deletedChapter = chapterRepository.findByLink("testLink");
        assertThat(deletedChapter).isNotPresent();
    }

    @Test
    @Transactional
    public void testAddChapterThrowsBranchNotFoundException() {
        ChapterRequest request = new ChapterRequest();
        request.setTitle("New Chapter");
        request.setNumber(1);
        request.setText("Chapter text");

        assertThrows(BranchNotFoundException.class, () -> {
            chapterService.add(request, "nonExistentBranch");
        });
    }
}
