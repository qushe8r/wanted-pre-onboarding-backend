package com.wanted.preonboardingbackend.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SingleResponse<T> {

    private T data;

}
