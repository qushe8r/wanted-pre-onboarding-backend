package com.wanted.preonboardingbackend.company.service;

import com.wanted.preonboardingbackend.company.dto.CompanyPost;
import com.wanted.preonboardingbackend.company.dto.CompanyResponse;
import com.wanted.preonboardingbackend.company.entity.Company;
import com.wanted.preonboardingbackend.company.mapper.CompanyMapper;
import com.wanted.preonboardingbackend.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    private final CompanyMapper companyMapper;

    @Override
    public CompanyResponse save(CompanyPost post) {
        Company company = companyMapper.toEntity(post);
        Company saved = companyRepository.save(company);
        return companyMapper.toResponse(saved);
    }

}
