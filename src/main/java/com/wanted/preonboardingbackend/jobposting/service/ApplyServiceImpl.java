package com.wanted.preonboardingbackend.jobposting.service;

import com.wanted.preonboardingbackend.jobposting.dto.ApplyPost;
import com.wanted.preonboardingbackend.jobposting.dto.ApplyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplyServiceImpl implements ApplyService {

    @Override
    public ApplyResponse save(Long jobPostingId, ApplyPost post) {
        return null;
    }

}
