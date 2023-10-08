package com.wanted.preonboardingbackend.jobposting.service;

import com.wanted.preonboardingbackend.jobposting.dto.JobPostingPost;
import com.wanted.preonboardingbackend.jobposting.dto.JobPostingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobPostingServiceImpl implements JobPostingService {
    
    @Override
    public JobPostingResponse save(JobPostingPost post) {
        return null;
    }

}
