package com.wanted.preonboardingbackend.jobposting.mapper;

import com.wanted.preonboardingbackend.jobposting.dto.ApplyPost;
import com.wanted.preonboardingbackend.jobposting.dto.ApplyResponse;
import com.wanted.preonboardingbackend.jobposting.entity.Apply;
import com.wanted.preonboardingbackend.jobposting.entity.JobPosting;
import com.wanted.preonboardingbackend.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ApplyMapper {

    public Apply toEntity(Long jobPostingId, ApplyPost apply) {
        return Apply.builder()
                .user(buildUser(apply))
                .jobPosting(buildJobPosting(jobPostingId))
                .build();
    }

    public ApplyResponse toResponse(Apply apply) {
        return ApplyResponse.builder()
                .applyId(apply.getId())
                .userId(apply.getUser().getId())
                .jobPostingId(apply.getJobPosting().getId())
                .build();
    }

    private User buildUser(ApplyPost apply) {
        return User.builder().id(apply.getUserId()).build();
    }

    private JobPosting buildJobPosting(Long jobPostingId) {
        return JobPosting.builder().id(jobPostingId).build();
    }

}
