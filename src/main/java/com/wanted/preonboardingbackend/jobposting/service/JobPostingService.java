package com.wanted.preonboardingbackend.jobposting.service;

import com.wanted.preonboardingbackend.jobposting.dto.JobPostingDetailResponse;
import com.wanted.preonboardingbackend.jobposting.dto.JobPostingPatch;
import com.wanted.preonboardingbackend.jobposting.dto.JobPostingPost;
import com.wanted.preonboardingbackend.jobposting.dto.JobPostingResponse;

import java.util.List;

public interface JobPostingService {

    JobPostingResponse save(JobPostingPost post);

    List<JobPostingResponse> getPosts(String searchCond);

    JobPostingResponse update(Long jobPostingId, JobPostingPatch patch);

    JobPostingDetailResponse getPost(Long jobPostingId);

}
