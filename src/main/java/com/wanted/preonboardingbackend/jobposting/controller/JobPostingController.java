package com.wanted.preonboardingbackend.jobposting.controller;

import com.wanted.preonboardingbackend.jobposting.dto.JobPostingDetailResponse;
import com.wanted.preonboardingbackend.jobposting.dto.JobPostingPatch;
import com.wanted.preonboardingbackend.jobposting.dto.JobPostingPost;
import com.wanted.preonboardingbackend.jobposting.dto.JobPostingResponse;
import com.wanted.preonboardingbackend.jobposting.service.ApplyService;
import com.wanted.preonboardingbackend.jobposting.service.JobPostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<JobPostingResponse>> getPosts(@RequestParam(required = false) String search) {
        List<JobPostingResponse> responses = jobPostingService.getPosts(search);
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/{jobPostingId}")
    public ResponseEntity<JobPostingResponse> modify(@PathVariable Long jobPostingId,
                                                     @RequestBody JobPostingPatch patch) {
        JobPostingResponse response = jobPostingService.update(jobPostingId, patch);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{jobPostingId}")
    public ResponseEntity<JobPostingDetailResponse> getPost(@PathVariable Long jobPostingId) {
        JobPostingDetailResponse response = jobPostingService.getPost(jobPostingId);
        return ResponseEntity.ok(response);
    }

}