package com.wanted.preonboardingbackend.jobposting.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Builder
public class JobPostingPost {

    @Positive(message = "0 보다 커야 합니다.")
    private Long companyId;

    @NotBlank(message = "공백일 수 없습니다.")
    private String position;

    @NotNull
    @PositiveOrZero(message = "0 보다 크거나 같아야 합니다.")
    private Long hiringBonus;

    @NotBlank(message = "공백일 수 없습니다.")
    private String content;

    @NotBlank(message = "공백일 수 없습니다.")
    private String skill;

}
