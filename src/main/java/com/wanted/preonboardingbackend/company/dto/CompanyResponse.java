package com.wanted.preonboardingbackend.company.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CompanyResponse {

    private Long companyId;

    private String companyName;

    private String country;

    private String city;

}
