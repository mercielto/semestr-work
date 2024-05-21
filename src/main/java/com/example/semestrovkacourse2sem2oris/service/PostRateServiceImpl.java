package com.example.semestrovkacourse2sem2oris.service;

import com.example.semestrovkacourse2sem2oris.dto.request.PostRateRequest;
import com.example.semestrovkacourse2sem2oris.model.BranchRateEntity;
import com.example.semestrovkacourse2sem2oris.model.PostEntity;
import com.example.semestrovkacourse2sem2oris.model.PostRateEntity;
import com.example.semestrovkacourse2sem2oris.model.UserEntity;
import com.example.semestrovkacourse2sem2oris.repository.PostRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostRateServiceImpl implements PostRateService {

    private final PostRateRepository repository;
    private final UserService userService;
    private final PostService postService;

    @Override
    public Integer getCurrentUserRate(String link) {
        UserEntity user = userService.getCurrentUser();
        PostEntity post = postService.getEntityByLink(link);
        Optional<PostRateEntity> postRateEntityOptional = repository.findByUserAndPost(user, post);

        return postRateEntityOptional.map(PostRateEntity::getRating).orElse(0);
    }

    @Override
    public void rate(String link, PostRateRequest request) {
        PostEntity post = postService.getEntityByLink(link);
        UserEntity user = userService.getCurrentUser();
        Optional<PostRateEntity> postRateEntityOptional = repository.findByUserAndPost(user, post);

        PostRateEntity postRateEntity;
        if (postRateEntityOptional.isPresent()) {
            postRateEntity = postRateEntityOptional.get();
            postRateEntity.setRating(request.getRating());
        } else {
            postRateEntity = PostRateEntity.builder()
                    .rating(request.getRating())
                    .post(post)
                    .user(user)
                    .build();
        }
        repository.save(postRateEntity);
    }
}
