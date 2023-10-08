package com.wanted.preonboardingbackend.jobposting.service;

import com.wanted.preonboardingbackend.jobposting.dto.ApplyPost;
import com.wanted.preonboardingbackend.jobposting.dto.ApplyResponse;
import com.wanted.preonboardingbackend.jobposting.entity.Apply;
import com.wanted.preonboardingbackend.jobposting.mapper.ApplyMapper;
import com.wanted.preonboardingbackend.jobposting.repositroy.ApplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplyServiceImpl implements ApplyService {

    private final ApplyRepository applyRepository;

    private final ApplyMapper applyMapper;

    @Override
    public ApplyResponse save(Long jobPostingId, ApplyPost post) {
        verifyExisting(jobPostingId, post);
        Apply apply = applyMapper.toEntity(jobPostingId, post);
        Apply saved = applyRepository.save(apply);

        return applyMapper.toResponse(saved);
    }

    private void verifyExisting(Long jobPostingId, ApplyPost post) {
        applyRepository.findByUser_IdAndJobPosting_Id(post.getUserId(), jobPostingId)
                .ifPresent(apply -> new RuntimeException());
    }

}
