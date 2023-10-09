package com.wanted.preonboardingbackend.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    APPLY_ALREADY_EXIST(409, "이미 지원하신 채용공고 입니다."),
    JOB_POSTING_NOT_FOUND(404, "채용공고를 찾을 수 없습니다.");

    private final int status;

    private final String message;

}
