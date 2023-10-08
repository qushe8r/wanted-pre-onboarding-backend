package com.wanted.preonboardingbackend.jobposting.repositroy.querydsl;

import com.wanted.preonboardingbackend.jobposting.entity.JobPosting;

import java.util.List;

public interface JobPostingRepositoryQueryDSL {

    List<JobPosting> search(String searchCond);

}
