package com.example.semestrovkacourse2sem2oris.service;

import com.example.semestrovkacourse2sem2oris.dto.request.BranchCommentRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.BranchCommentResponse;
import com.example.semestrovkacourse2sem2oris.mapper.BranchCommentMapper;
import com.example.semestrovkacourse2sem2oris.model.BranchCommentEntity;
import com.example.semestrovkacourse2sem2oris.model.BranchEntity;
import com.example.semestrovkacourse2sem2oris.model.UserEntity;
import com.example.semestrovkacourse2sem2oris.repository.BranchCommentRepository;
import com.example.semestrovkacourse2sem2oris.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchCommentServiceImpl implements BranchCommentService {

    private final BranchCommentRepository branchCommentRepository;
    private final BranchService branchService;
    private final UserService userService;
    private final BranchCommentMapper mapper;

    @Override
    public List<BranchCommentResponse> getByBranchLinkWithPagination(String link, int from, int count) {
        BranchEntity branch = branchService.getEntityByLink(link);
        Pageable pageable = PageRequest.of(from, count);
        return branchCommentRepository.findAllByBranch(branch, pageable).stream().map(mapper::toResponse).toList();
    }

    @Override
    public BranchCommentResponse createByBranchLink(String link, BranchCommentRequest request) {
        BranchEntity branch = branchService.getEntityByLink(link);
        UserEntity user = userService.getCurrentUser();
        BranchCommentEntity comment = mapper.toEntity(request);
        comment.setBranch(branch);
        comment.setUser(user);
        return mapper.toResponse(branchCommentRepository.save(comment));
    }
}
