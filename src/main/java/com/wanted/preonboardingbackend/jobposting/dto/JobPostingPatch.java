package com.wanted.preonboardingbackend.jobposting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobPostingPatch {

    //TODO: null 허용, "", " " 거부 만들기
    private String position;

    private Long hiringBonus;

    private String content;

    private String skill;

}
