package com.example.semestrovkacourse2sem2oris.service;

import com.example.semestrovkacourse2sem2oris.dto.request.BranchRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.BranchResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.BranchShortResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.BranchUserShortResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.ChapterResponse;
import com.example.semestrovkacourse2sem2oris.exception.BranchNotFoundException;
import com.example.semestrovkacourse2sem2oris.exception.PostNotFoundException;
import com.example.semestrovkacourse2sem2oris.mapper.BranchMapper;
import com.example.semestrovkacourse2sem2oris.model.*;
import com.example.semestrovkacourse2sem2oris.repository.BranchRepository;
import com.example.semestrovkacourse2sem2oris.repository.PostRepository;
import com.example.semestrovkacourse2sem2oris.util.LinkGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository repository;
    private final ChapterService chapterService;
    private final LinkGenerator linkGenerator;
    private final BranchMapper mapper;
    private final PostRepository postRepository;
    private final UserService userService;

    @Override
    public Long create(BranchEntity entity) {
        return repository.save(entity).getBranchId();
    }

    @Override
    public BranchEntity create(PostEntity postEntity) {
        BranchEntity branch = BranchEntity.builder().post(postEntity)
                .creator(postEntity.getCreator())
                .name("Основная")
                .link(linkGenerator.generateLink())
                .build();
        repository.save(branch);
        branch.getChapters().add(chapterService.create(branch));
        return branch;
    }

    @Override
    public BranchEntity getEntityByLink(String branchLink) {
        return repository.findByLink(branchLink).orElseThrow(() -> new BranchNotFoundException(branchLink));
    }

    public BranchEntity getMainBranch(List<BranchEntity> branches) {
        return branches.stream()
                .filter(BranchEntity::isMain)
                .findFirst()
                .orElseThrow(() -> new BranchNotFoundException("No main branch found"));
    }

    @Override
    public BranchResponse getMainBranchByPostLink(String link) {
        PostEntity postEntity = postRepository.findByWebLink(link).orElseThrow(() -> new PostNotFoundException(link));
        return mapper.toResponse(getMainBranch(postEntity.getBranches()));
    }

    @Override
    public BranchResponse getMain(List<BranchResponse> branches) {
        return branches.stream()
                .filter(BranchResponse::isMain)
                .findFirst()
                .orElseThrow(() -> new BranchNotFoundException("No main branch found"));
    }

    @Override
    public BranchShortResponse getShortByLink(String link) {
        return mapper.toShortResponse(getEntityByLink(link));
    }

    @Override
    public void update(String link, BranchRequest request) {
        repository.updateByLink(link, request.getDescription(), request.getName());
    }

    @Override
    public List<BranchUserShortResponse> getPublishedUserShortByPostLinkWithPagination(
            String link, Integer from, Integer count, SortType sortType) {

        PostEntity post = postRepository.findByWebLink(link).orElseThrow(() -> new PostNotFoundException(link));
        UserEntity user = userService.getCurrentUser();
        Pageable pageable = PageRequest.of(from, count);
        boolean published = true;

        Page<BranchEntity> branches;
        switch (sortType) {
            case AVERAGE_RATING_ASC : {
                branches = repository.findAllByPostAndPublishedOrderByAverageRatingAsc(post, pageable, published);
                break;
            }
            case AVERAGE_RATING_DESC: {
                branches = repository.findAllByPostAndPublishedOrderByAverageRatingDesc(post, pageable, published);
                break;
            }
            case NUMBER_OF_VOTES_ASC: {
                branches = repository.findAllByPostAndPublishedOrderByRatesCountAsc(post, pageable, published);
                break;
            }
            case NUMBER_OF_VOTES_DESC: {
                branches = repository.findAllByPostAndPublishedOrderByRatesCountDesc(post, pageable, published);
                break;
            }
            default: {
                branches = repository.findAllByPostAndPublished(post, pageable, published);
            }
        }

        return branches.stream().map(
                branch -> mapper.toBranchUserShortResponse(
                        branch,
                        getUserBranchRate(branch, user)
                ))
                .toList();
    }

    // TODO: сделать ограничение на вставку номера главы
    @Transactional
    @Override
    public BranchResponse createByBranchLink(String link, Integer number) {
        BranchEntity prevBranch = getEntityByLink(link);
        BranchEntity parentBranch = findParentBranchRecursively(prevBranch, number);
        BranchEntity branch = BranchEntity.builder()
                .link(linkGenerator.generateLink())
                .creator(userService.getCurrentUser())
                .parentBranch(parentBranch)
                .post(parentBranch.getPost())
                .build();
        repository.save(branch);
        branch.getChapters().add(chapterService.create(branch, number));
        return mapper.toResponse(branch);
    }

    @Override
    public Map<Integer, List<ChapterResponse>> getOrderedContentByBranchLink(String branchLink) {
        BranchEntity branch = getEntityByLink(branchLink);
        Map<Integer, List<ChapterResponse>> content = new HashMap<>();
        chapterService.addChaptersFromOneEnd(content, branch);
        return content;
    }

    @Override
    public BranchResponse getByLink(String link) {
        return mapper.toResponse(getEntityByLink(link));
    }

    // TODO: check
    private BranchEntity findParentBranchRecursively(BranchEntity branch, Integer number) {
        // не включительно, тк ветвление происходит от корневой ветки
        ChapterEntity firstChapter = chapterService.getFirstChapter(branch);
        if (firstChapter.getNumber() < number) {
            return branch;
        }

        BranchEntity parentBranch = branch.getParentBranch();
        if (parentBranch == null) {
            return branch;
        }
        return findParentBranchRecursively(parentBranch, number);
    }

    private Integer getUserBranchRate(BranchEntity branch, UserEntity user) {
        for (BranchRateEntity branchRateEntity : user.getRatedBranches()) {
            if (branchRateEntity.getBranch().getBranchId().equals(branch.getBranchId())) {
                return branchRateEntity.getRating();
            }
        }
        return 0;
    }
}
