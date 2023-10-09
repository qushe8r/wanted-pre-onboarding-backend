package com.wanted.preonboardingbackend.jobposting.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JobPostingPostResponse {

    private Long jobPostingId;

    private String position;

    private Long hiringBonus;

    private String content;

    private String skill;

}
