package com.wanted.preonboardingbackend.jobposting.repositroy;

import com.wanted.preonboardingbackend.jobposting.entity.JobPosting;
import com.wanted.preonboardingbackend.jobposting.repositroy.querydsl.JobPostingRepositoryQueryDSL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long>, JobPostingRepositoryQueryDSL {
}
