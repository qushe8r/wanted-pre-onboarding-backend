package com.wanted.preonboardingbackend.jobposting.dto;

import com.wanted.preonboardingbackend.validator.NotSpace;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.PositiveOrZero;

@Getter
@Builder
public class JobPostingPatch {

    @NotSpace
    private String position;

    @PositiveOrZero(message = "0 보다 크거나 같아야 합니다.")
    private Long hiringBonus;

    @NotSpace
    private String content;

    @NotSpace
    private String skill;

}
