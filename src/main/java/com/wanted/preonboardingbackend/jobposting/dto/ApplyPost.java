package com.wanted.preonboardingbackend.jobposting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyPost {

    @Positive(message = "0보다 커야 합니다.")
    private Long userId;

}
