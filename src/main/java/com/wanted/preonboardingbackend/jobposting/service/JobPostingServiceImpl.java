package com.wanted.preonboardingbackend.jobposting.service;

import com.wanted.preonboardingbackend.jobposting.dto.JobPostingDetailResponse;
import com.wanted.preonboardingbackend.jobposting.dto.JobPostingPatch;
import com.wanted.preonboardingbackend.jobposting.dto.JobPostingPost;
import com.wanted.preonboardingbackend.jobposting.dto.JobPostingResponse;
import com.wanted.preonboardingbackend.jobposting.entity.JobPosting;
import com.wanted.preonboardingbackend.jobposting.mapper.JobPostingMapper;
import com.wanted.preonboardingbackend.jobposting.repositroy.JobPostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<JobPostingResponse> getPosts(String searchCond) {
        List<JobPosting> all = jobPostingRepository.search(searchCond);
        return all.stream().map(jobPostingMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public JobPostingResponse update(Long jobPostingId, JobPostingPatch patch) {
        JobPosting jobPosting = jobPostingRepository.findById(jobPostingId)
                .orElseThrow(RuntimeException::new);

        jobPosting.update(patch.getPosition(), patch.getHiringBonus(), patch.getContent(), patch.getSkill());
        return jobPostingMapper.toResponse(jobPosting);
    }

    @Override
    public JobPostingDetailResponse getPost(Long jobPostingId) {
        JobPosting jobPosting = jobPostingRepository.getPost(jobPostingId).orElseThrow(RuntimeException::new);
        return jobPostingMapper.toDetailResponse(jobPosting);
    }

    @Override
    public void remove(Long jobPostingId) {

    }

}
