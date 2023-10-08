package com.wanted.preonboardingbackend.jobposting.repositroy;

import com.wanted.preonboardingbackend.jobposting.entity.JobPosting;
import com.wanted.preonboardingbackend.jobposting.repositroy.querydsl.JobPostingRepositoryQueryDSL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long>, JobPostingRepositoryQueryDSL {

    @Query("SELECT p FROM JobPosting p JOIN FETCH p.company c JOIN FETCH c.jobPostings WHERE p.id =:jobPostingId")
    Optional<JobPosting> getPost(Long jobPostingId);

}
