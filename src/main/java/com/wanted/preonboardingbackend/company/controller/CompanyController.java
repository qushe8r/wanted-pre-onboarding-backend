package com.wanted.preonboardingbackend.company.controller;

import com.wanted.preonboardingbackend.company.dto.CompanyPost;
import com.wanted.preonboardingbackend.company.dto.CompanyResponse;
import com.wanted.preonboardingbackend.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    private static final String COMPANY_URI = "/companies";

    @PostMapping
    public ResponseEntity<CompanyResponse> register(@RequestBody CompanyPost post) {
        CompanyResponse response = companyService.save(post);
        URI uri = URI.create(COMPANY_URI + response.getCompanyId());
        return ResponseEntity.created(uri).body(response);
    }

}
