package com.wanted.preonboardingbackend.company.service;

import com.wanted.preonboardingbackend.company.dto.CompanyPost;
import com.wanted.preonboardingbackend.company.dto.CompanyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    @Override
    public CompanyResponse save(CompanyPost post) {
        return null;
    }

}
