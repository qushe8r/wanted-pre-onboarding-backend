package com.wanted.preonboardingbackend.company.service;

import com.wanted.preonboardingbackend.company.dto.CompanyPost;
import com.wanted.preonboardingbackend.company.dto.CompanyResponse;

public interface CompanyService {

    CompanyResponse save(CompanyPost post);

}
