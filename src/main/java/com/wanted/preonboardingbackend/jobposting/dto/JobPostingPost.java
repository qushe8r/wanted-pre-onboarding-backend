package com.wanted.preonboardingbackend.jobposting.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public class JobPostingPost {

    @NotBlank
    private Long companyId;

    @NotBlank
    private String position;

    @NotNull
    private Long hiringBonus;

    @NotBlank
    private String content;

    @NotBlank
    private String skill;

}
