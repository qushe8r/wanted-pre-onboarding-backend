package com.wanted.preonboardingbackend.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyPost {

    @NotBlank
    private String name;

    @NotBlank
    private String country;

    @NotBlank
    private String city;

}
