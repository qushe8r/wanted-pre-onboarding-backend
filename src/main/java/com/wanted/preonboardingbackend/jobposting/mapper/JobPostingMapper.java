package com.wanted.preonboardingbackend.jobposting.mapper;

import com.wanted.preonboardingbackend.company.entity.Company;
import com.wanted.preonboardingbackend.jobposting.dto.JobPostingPost;
import com.wanted.preonboardingbackend.jobposting.dto.JobPostingResponse;
import com.wanted.preonboardingbackend.jobposting.entity.JobPosting;
import org.springframework.stereotype.Component;

@Component
public class JobPostingMapper {

    public JobPosting toEntity(JobPostingPost post) {
        return JobPosting.builder()
                .position(post.getPosition())
                .hiringBonus(post.getHiringBonus())
                .content(post.getContent())
                .skill(post.getSkill())
                .company(buildCompany(post))
                .build();
    }

    public JobPostingResponse toResponse(JobPosting jobPosting) {
        boolean isNull = jobPosting.getCompany() == null;
        return JobPostingResponse.builder()
                .companyName(isNull ? null : jobPosting.getCompany().getName())
                .country(isNull ? null : jobPosting.getCompany().getCountry())
                .city(isNull ? null : jobPosting.getCompany().getCity())
                .jobPostingId(jobPosting.getId())
                .position(jobPosting.getPosition())
                .hiringBonus(jobPosting.getHiringBonus())
                .content(jobPosting.getContent())
                .skill(jobPosting.getSkill())
                .build();
    }

    private Company buildCompany(JobPostingPost post) {
        return Company.builder().id(post.getCompanyId()).build();
    }

}
