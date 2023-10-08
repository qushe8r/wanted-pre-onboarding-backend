package com.wanted.preonboardingbackend.jobposting.controller;

import com.wanted.preonboardingbackend.jobposting.dto.JobPostingPost;
import com.wanted.preonboardingbackend.jobposting.dto.JobPostingResponse;
import com.wanted.preonboardingbackend.jobposting.service.ApplyService;
import com.wanted.preonboardingbackend.jobposting.service.JobPostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/job-postings")
public class JobPostingController {

    private final JobPostingService jobPostingService;

    private static final String JOB_POSTING_URI = "/job-postings";

    @PostMapping
    public ResponseEntity<JobPostingResponse> write(@RequestBody JobPostingPost post) {
        JobPostingResponse response = jobPostingService.save(post);
        URI uri = URI.create(JOB_POSTING_URI);
        return ResponseEntity.created(uri).body(response);
    }

}