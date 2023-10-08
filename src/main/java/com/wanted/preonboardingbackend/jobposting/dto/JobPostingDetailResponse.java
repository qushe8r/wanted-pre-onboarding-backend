package com.wanted.preonboardingbackend.jobposting.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class JobPostingDetailResponse {

    private Long jobPostingId;

    private String companyName;

    private String country;

    private String city;

    private String position;

    private Long hiringBonus;

    private String content;

    private String skill;

    @Builder.Default
    private List<Long> jobPostings = new ArrayList<>();

}
