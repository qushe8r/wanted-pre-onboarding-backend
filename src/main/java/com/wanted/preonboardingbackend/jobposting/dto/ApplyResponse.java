package com.wanted.preonboardingbackend.jobposting.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApplyResponse {

    private Long applyId;

    private Long userId;

    private Long jobPostingId;

}
