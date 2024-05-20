package com.example.semestrovkacourse2sem2oris.service;

import com.example.semestrovkacourse2sem2oris.dto.request.BranchRateRequest;
import com.example.semestrovkacourse2sem2oris.model.BranchEntity;
import com.example.semestrovkacourse2sem2oris.model.BranchRateEntity;
import com.example.semestrovkacourse2sem2oris.model.UserEntity;
import com.example.semestrovkacourse2sem2oris.repository.BranchRateRepository;
import com.example.semestrovkacourse2sem2oris.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BranchRateServiceImpl implements BranchRateService {

    private final BranchRateRepository repository;
    private final BranchService branchService;
    private final UserService userService;

    @Override
    public void rate(String branchLink, BranchRateRequest request) {
        BranchEntity branchEntity = branchService.getEntityByLink(branchLink);
        UserEntity userEntity = userService.getCurrentUser();
        Optional<BranchRateEntity> branchRateEntityOptional = repository.findByUserAndBranch(userEntity, branchEntity);

        BranchRateEntity branchRateEntity;
        if (branchRateEntityOptional.isEmpty()) {
             branchRateEntity = BranchRateEntity.builder()
                    .branch(branchEntity)
                    .rating(request.getRate())
                    .user(userEntity)
                    .build();
        } else {
            branchRateEntity = branchRateEntityOptional.get();
            branchRateEntity.setRating(request.getRate());
        }

        repository.save(branchRateEntity);
    }

    @Override
    public Integer getCurrentUserRate(String branchLink) {
        UserEntity user = userService.getCurrentUser();
        BranchEntity branch = branchService.getEntityByLink(branchLink);
        Optional<BranchRateEntity> branchRateEntityOptional = repository.findByUserAndBranch(user, branch);

        if (branchRateEntityOptional.isPresent()) {
            return branchRateEntityOptional.get().getRating();
        }
        return 0;
    }
}
