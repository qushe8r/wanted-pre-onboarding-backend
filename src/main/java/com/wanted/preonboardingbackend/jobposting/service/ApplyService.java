package com.wanted.preonboardingbackend.jobposting.service;

import com.wanted.preonboardingbackend.jobposting.dto.ApplyPost;
import com.wanted.preonboardingbackend.jobposting.dto.ApplyResponse;

public interface ApplyService {

    ApplyResponse save(Long jobPostingId, ApplyPost post);

}
