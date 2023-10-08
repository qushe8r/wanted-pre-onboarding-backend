package com.wanted.preonboardingbackend.jobposting.service;

import com.wanted.preonboardingbackend.jobposting.dto.JobPostingPost;
import com.wanted.preonboardingbackend.jobposting.dto.JobPostingResponse;

public interface JobPostingService {

    JobPostingResponse save(JobPostingPost post);

}
