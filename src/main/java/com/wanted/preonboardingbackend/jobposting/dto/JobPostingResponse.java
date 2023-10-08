package com.wanted.preonboardingbackend.jobposting.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JobPostingResponse {

    private Long jobPostingId;

    private String companyName;

    private String country;

    private String city;

    private String position;

    private Long hiringBonus;

    private String content;

    private String skill;

}
