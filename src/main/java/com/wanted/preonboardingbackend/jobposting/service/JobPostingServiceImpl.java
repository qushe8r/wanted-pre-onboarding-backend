package com.wanted.preonboardingbackend.jobposting.service;

import com.wanted.preonboardingbackend.jobposting.dto.JobPostingPost;
import com.wanted.preonboardingbackend.jobposting.dto.JobPostingResponse;
import com.wanted.preonboardingbackend.jobposting.entity.JobPosting;
import com.wanted.preonboardingbackend.jobposting.mapper.JobPostingMapper;
import com.wanted.preonboardingbackend.jobposting.repositroy.JobPostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobPostingServiceImpl implements JobPostingService {

    private final JobPostingRepository jobPostingRepository;

    private final JobPostingMapper jobPostingMapper;

    @Override
    public JobPostingResponse save(JobPostingPost post) {
        JobPosting jobPosting = jobPostingMapper.toEntity(post);
        JobPosting saved = jobPostingRepository.save(jobPosting);
        return jobPostingMapper.toResponse(saved);
    }

}
