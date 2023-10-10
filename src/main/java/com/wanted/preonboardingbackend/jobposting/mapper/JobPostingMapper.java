package com.wanted.preonboardingbackend.jobposting.mapper;

import com.wanted.preonboardingbackend.company.entity.Company;
import com.wanted.preonboardingbackend.jobposting.dto.JobPostingDetailResponse;
import com.wanted.preonboardingbackend.jobposting.dto.JobPostingPost;
import com.wanted.preonboardingbackend.jobposting.dto.JobPostingPostResponse;
import com.wanted.preonboardingbackend.jobposting.dto.JobPostingResponse;
import com.wanted.preonboardingbackend.jobposting.entity.JobPosting;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public JobPostingPostResponse toPostResponse(JobPosting jobPosting) {
        return JobPostingPostResponse.builder()
                .jobPostingId(jobPosting.getId())
                .position(jobPosting.getPosition())
                .hiringBonus(jobPosting.getHiringBonus())
                .content(jobPosting.getContent())
                .skill(jobPosting.getSkill())
                .build();
    }

    public JobPostingResponse toResponse(JobPosting jobPosting) {
        Company company = jobPosting.getCompany();
        return JobPostingResponse.builder()
                .jobPostingId(jobPosting.getId())
                .name(company.getName())
                .country(company.getCountry())
                .city(company.getCity())
                .position(jobPosting.getPosition())
                .hiringBonus(jobPosting.getHiringBonus())
                .content(jobPosting.getContent())
                .skill(jobPosting.getSkill())
                .build();
    }

    public JobPostingDetailResponse toDetailResponse(JobPosting jobPosting) {
        return JobPostingDetailResponse.builder()
                .jobPostingId(jobPosting.getId())
                .name(jobPosting.getCompany().getName())
                .country(jobPosting.getCompany().getCountry())
                .city(jobPosting.getCompany().getCity())
                .position(jobPosting.getPosition())
                .hiringBonus(jobPosting.getHiringBonus())
                .skill(jobPosting.getSkill())
                .content(jobPosting.getContent())
                .jobPostings(writtenBySameCompany(jobPosting))
                .build();
    }

    private Company buildCompany(JobPostingPost post) {
        return Company.builder().id(post.getCompanyId()).build();
    }

    private List<Long> writtenBySameCompany(JobPosting jobPosting) {
        return jobPosting.getCompany().getJobPostings().stream().map(JobPosting::getId).collect(Collectors.toList());
    }

}
