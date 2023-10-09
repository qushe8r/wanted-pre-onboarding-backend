package com.wanted.preonboardingbackend.jobposting.service;

import com.wanted.preonboardingbackend.exception.BusinessLogicException;
import com.wanted.preonboardingbackend.exception.ExceptionCode;
import com.wanted.preonboardingbackend.jobposting.dto.*;
import com.wanted.preonboardingbackend.jobposting.entity.JobPosting;
import com.wanted.preonboardingbackend.jobposting.mapper.JobPostingMapper;
import com.wanted.preonboardingbackend.jobposting.repositroy.JobPostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobPostingServiceImpl implements JobPostingService {

    private final JobPostingRepository jobPostingRepository;

    private final JobPostingMapper jobPostingMapper;

    @Override
    public JobPostingPostResponse save(JobPostingPost post) {
        JobPosting jobPosting = jobPostingMapper.toEntity(post);
        JobPosting saved = jobPostingRepository.save(jobPosting);
        return jobPostingMapper.toPostResponse(saved);
    }

    @Override
    public List<JobPostingResponse> getPosts(String searchCond) {
        List<JobPosting> all = jobPostingRepository.search(searchCond);
        return all.stream().map(jobPostingMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public JobPostingResponse update(Long jobPostingId, JobPostingPatch patch) {
        JobPosting jobPosting = findVerifedJobPosting(jobPostingId);
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
        jobPostingRepository.findById(jobPostingId)
                .ifPresent(jobPostingRepository::delete);
    }

    private JobPosting findVerifedJobPosting(Long jobPostingId) {
        return jobPostingRepository.findById(jobPostingId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.JOB_POSTING_NOT_FOUND));
    }

}
