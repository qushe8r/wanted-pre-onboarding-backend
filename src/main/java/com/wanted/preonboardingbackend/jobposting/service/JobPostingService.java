package com.wanted.preonboardingbackend.jobposting.service;

import com.wanted.preonboardingbackend.jobposting.dto.*;

import java.util.List;

public interface JobPostingService {

    JobPostingPostResponse save(JobPostingPost post);

    List<JobPostingResponse> getPosts(String searchCond);

    JobPostingResponse update(Long jobPostingId, JobPostingPatch patch);

    JobPostingDetailResponse getPost(Long jobPostingId);

    void remove(Long jobPostingId);

}
