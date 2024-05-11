package com.example.semestrovkacourse2sem2oris.service;

import com.example.semestrovkacourse2sem2oris.dto.request.PostRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.PostResponse;
import com.example.semestrovkacourse2sem2oris.exception.PostNotFoundException;
import com.example.semestrovkacourse2sem2oris.mapper.PostMapper;
import com.example.semestrovkacourse2sem2oris.model.ChapterEntity;
import com.example.semestrovkacourse2sem2oris.model.PostEntity;
import com.example.semestrovkacourse2sem2oris.model.UserEntity;
import com.example.semestrovkacourse2sem2oris.repository.PostRepository;
import com.example.semestrovkacourse2sem2oris.util.LinkGenerator;
import com.example.semestrovkacourse2sem2oris.util.ObjectCopier;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.management.AttributeNotFoundException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository repository;
    private final PostMapper mapper;
    private final UserService userService;
    private final LinkGenerator generator;

    @Override
    public void create(PostRequest request) {
        PostEntity postEntity = mapper.toEntity(request);
        postEntity.setWebLink(generator.generateLink());
        setCreator(postEntity);

        repository.save(postEntity);
    }

    @Override
    public PostResponse getByLink(String link) {
        return mapper.toResponse(
                getEntityByLink(link)
        );
    }

    @Override
    public PostEntity getEntityByLink(String link) {
        return repository.findByWebLink(link).orElseThrow(() -> new PostNotFoundException(link));
    }

    @Override
    public PostResponse create() {
        PostEntity entity = PostEntity.builder()
                .webLink(generator.generateLink())
                .imagePath("default")
                .build();
        setCreator(entity);
        repository.save(entity);
        return mapper.toResponse(entity);
    }

    @Override
    public PostResponse getPostFromSession(HttpSession session) throws AttributeNotFoundException {
        Object post = session.getAttribute("savedPost");
        if (post instanceof PostEntity) {
            return mapper.toResponse((PostEntity) post);
        }
        throw new AttributeNotFoundException();
    }

    @Override
    public void saveChanges(PostRequest postRequest) {
        String link = postRequest.getWebLink();
        PostEntity savedPostEntity = repository.findByWebLink(link).orElseThrow(() -> new PostNotFoundException(link));
        PostEntity postEntity = mapper.toEntity(postRequest);

        ObjectCopier.copyNotNullFields(savedPostEntity, postEntity);

        postEntity.setPostId(savedPostEntity.getPostId());
        postEntity.setCreator(savedPostEntity.getCreator());
        repository.save(postEntity);
    }

    @Override
    public PostResponse deleteChapter(String postLink, Integer chapterNumber) {
        PostEntity postEntity = getEntityByLink(postLink);
        List<ChapterEntity> chapters = postEntity.getChapters();

        ChapterEntity chapterToRemove = null;
        for (ChapterEntity chapter : chapters) {
            if (Objects.equals(chapter.getNumber(), chapterNumber)) {
                chapterToRemove = chapter;
            } else if (chapterToRemove != null) {
                chapter.setNumber(chapter.getNumber() - 1);
            }
        }
        chapters.remove(chapterToRemove);
        postEntity = repository.save(postEntity);
        return mapper.toResponse(postEntity);
    }

    public void setCreator(PostEntity postEntity) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = userService.getByLogin(login);
        postEntity.setCreator(userEntity);
    }
}
