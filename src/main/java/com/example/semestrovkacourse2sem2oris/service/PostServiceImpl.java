package com.example.semestrovkacourse2sem2oris.service;

import com.example.semestrovkacourse2sem2oris.dto.request.PostRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.PostResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.PostShortResponse;
import com.example.semestrovkacourse2sem2oris.exception.PostNotFoundException;
import com.example.semestrovkacourse2sem2oris.mapper.PostMapper;
import com.example.semestrovkacourse2sem2oris.model.BranchEntity;
import com.example.semestrovkacourse2sem2oris.model.ChapterEntity;
import com.example.semestrovkacourse2sem2oris.model.PostEntity;
import com.example.semestrovkacourse2sem2oris.model.UserEntity;
import com.example.semestrovkacourse2sem2oris.repository.BranchRepository;
import com.example.semestrovkacourse2sem2oris.repository.ChapterRepository;
import com.example.semestrovkacourse2sem2oris.repository.PostRepository;
import com.example.semestrovkacourse2sem2oris.util.LinkGenerator;
import com.example.semestrovkacourse2sem2oris.util.ObjectCopier;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.management.AttributeNotFoundException;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ChapterRepository chapterRepository;
    private final PostMapper mapper;
    private final UserService userService;
    private final LinkGenerator generator;
    private final ChapterService chapterService;
    private final BranchRepository branchRepository;

    @Override
    public void create(PostRequest request) {
        PostEntity postEntity = mapper.toEntity(request);
        postEntity.setWebLink(generator.generateLink());
        setCreator(postEntity);
        postEntity.setPublished(true);

        postRepository.save(postEntity);
    }

    @Override
    public PostResponse getByLink(String link) {
        return mapper.toResponse(
                getEntityByLink(link)
        );
    }

    @Override
    public PostEntity getEntityByLink(String link) {
        return postRepository.findByWebLink(link).orElseThrow(() -> new PostNotFoundException(link));
    }

    @Override
    public PostResponse create() {
        BranchEntity branchEntity =
        PostEntity entity = PostEntity.builder()
                .webLink(generator.generateLink())
                .imagePath("default")
                .build();
        setCreator(entity);
        postRepository.save(entity);
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
    public void saveChanges(PostRequest postRequest, String link) {
        PostEntity savedPostEntity = getEntityByLink(link);
        PostEntity postEntity = mapper.toEntity(postRequest);

        ObjectCopier.copyNotNullFields(savedPostEntity, postEntity);

        postEntity.setPostId(savedPostEntity.getPostId());
        postEntity.setCreator(savedPostEntity.getCreator());
        postRepository.save(postEntity);
    }

    @Override
    public PostResponse deleteChapter(String postLink, Integer chapterNumber) {
//        PostEntity postEntity = getEntityByLink(postLink);
//        List<ChapterEntity> chapters = postEntity.getChapters();
//
//        ChapterEntity chapterToRemove = null;
//        for (ChapterEntity chapter : chapters) {
//            if (Objects.equals(chapter.getNumber(), chapterNumber)) {
//                chapterToRemove = chapter;
//            } else if (chapterToRemove != null) {
//                chapter.setNumber(chapter.getNumber() - 1);
//            }
//        }
//        chapters.remove(chapterToRemove);
//        chapterRepository.delete(chapterToRemove);
//        postEntity = postRepository.save(postEntity);
//        return mapper.toResponse(postEntity);
        return null;
    }

    @Override
    public PostShortResponse getByChapterLink(String chapterLink) {
        ChapterEntity chapterEntity = chapterService.getEntityByLink(chapterLink);
        PostEntity postEntity = postRepository.findById(chapterEntity.getId()).orElseThrow(() -> new PostNotFoundException("null"));
        return mapper.toShortResponse(postEntity);
    }

    @Override
    public PostShortResponse getShortByLink(String link) {
        return mapper.toShortResponse(getEntityByLink(link));
    }

    @Override
    public void publish(PostRequest request, String link) {
        PostEntity savedPostEntity = getEntityByLink(link);
        PostEntity postEntity = mapper.toEntity(request);
        ObjectCopier.copyNotNullFields(savedPostEntity, postEntity);

        postEntity.setPostId(savedPostEntity.getPostId());
        postEntity.setCreator(savedPostEntity.getCreator());
        postEntity.setPublished(true);
        postRepository.save(postEntity);
    }

    public void setCreator(PostEntity postEntity) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = userService.getByLogin(login);
        postEntity.setCreator(userEntity);
    }
}
