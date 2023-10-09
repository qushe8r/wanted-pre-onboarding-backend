package com.wanted.preonboardingbackend.company.mapper;

import com.wanted.preonboardingbackend.company.dto.CompanyPost;
import com.wanted.preonboardingbackend.company.dto.CompanyResponse;
import com.wanted.preonboardingbackend.company.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public Company toEntity(CompanyPost post) {
        return Company.builder()
                .name(post.getName())
                .country(post.getCountry())
                .city(post.getCity())
                .build();
    }

    public CompanyResponse toResponse(Company company) {
        return CompanyResponse.builder()
                .companyId(company.getId())
                .name(company.getName())
                .country(company.getCountry())
                .city(company.getCity())
                .build();
    }

}
