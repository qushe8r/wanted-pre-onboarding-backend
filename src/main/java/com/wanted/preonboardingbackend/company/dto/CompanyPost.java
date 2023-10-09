package com.wanted.preonboardingbackend.company.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class CompanyPost {

    @NotBlank(message = "공백일 수 없습니다.")
    private String name;

    @NotBlank(message = "공백일 수 없습니다.")
    private String country;

    @NotBlank(message = "공백일 수 없습니다.")
    private String city;

}
