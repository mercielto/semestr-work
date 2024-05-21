package com.example.semestrovkacourse2sem2oris.service;

import com.example.semestrovkacourse2sem2oris.dto.request.PostReadRequest;
import com.example.semestrovkacourse2sem2oris.dto.request.PostRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.PostResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.PostShortResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.PostUserShortResponse;
import com.example.semestrovkacourse2sem2oris.exception.PostNotFoundException;
import com.example.semestrovkacourse2sem2oris.exception.PostReadStatusNotFoundException;
import com.example.semestrovkacourse2sem2oris.mapper.PostMapper;
import com.example.semestrovkacourse2sem2oris.model.*;
import com.example.semestrovkacourse2sem2oris.repository.PostRepository;
import com.example.semestrovkacourse2sem2oris.util.LinkGenerator;
import com.example.semestrovkacourse2sem2oris.util.ObjectCopier;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.AttributeNotFoundException;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper mapper;
    private final UserService userService;
    private final LinkGenerator generator;
    private final ChapterService chapterService;
    private final BranchService branchService;

    @Override
    public void create(PostRequest request) {
        PostEntity postEntity = mapper.toEntity(request);
        postEntity.setWebLink(generator.generateLink());
        postEntity.setCreator(userService.getCurrentUser());
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

    @Transactional
    @Override
    public PostResponse create() {
        PostEntity postEntity = PostEntity.builder()
                .webLink(generator.generateLink())
                .creator(userService.getCurrentUser())
                .build();
        postRepository.save(postEntity);
        postEntity.getBranches().add(branchService.create(postEntity));
        postRepository.save(postEntity);
        return mapper.toResponse(postEntity);
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
        postEntity.setBranches(savedPostEntity.getBranches());
        postEntity.setEditors(savedPostEntity.getEditors());
        ObjectCopier.copyNotNullFields(savedPostEntity, postEntity);

        postEntity.setPostId(savedPostEntity.getPostId());
        postEntity.setCreator(savedPostEntity.getCreator());
        postRepository.save(postEntity);
    }

    @Override
    public PostShortResponse getByChapterLink(String chapterLink) {
        ChapterEntity chapterEntity = chapterService.getEntityByLink(chapterLink);
        return mapper.toShortResponse(chapterEntity.getBranch().getPost());
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

    @Override
    public PostShortResponse getShortByBranchLink(String branchLink) {
        return mapper.toShortResponse(branchService.getEntityByLink(branchLink).getPost());
    }

    @Override
    public void delete(String link) {
        postRepository.deleteByWebLink(link);
    }

    @Override
    public void changeReadStatus(String link, PostReadRequest request) {
        PostEntity post = getEntityByLink(link);
        UserEntity user = userService.getCurrentUser();

        switch (request.getStatus()) {
            case VIEWED -> post.getReadUsers().add(user);
            case NOT_VIEWED -> post.getReadUsers().remove(user);
            default -> throw new PostReadStatusNotFoundException(request.getStatus());
        }
        postRepository.save(post);
    }

    @Override
    public PostReadStatus isCurrentUserRead(PostEntity entity) {
        UserEntity user = userService.getCurrentUser();
        if (user.getReadPosts().contains(entity)) {
            return PostReadStatus.VIEWED;
        }
        return PostReadStatus.NOT_VIEWED;
    }

    @Override
    public PostUserShortResponse getUserShortByLink(String link) {
        return mapper.toPostUserShortResponse(getEntityByLink(link), this);
    }
}
