package com.wanted.preonboardingbackend.jobposting.controller;

import com.wanted.preonboardingbackend.jobposting.dto.*;
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

    private final ApplyService applyService;

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

    @DeleteMapping("/{jobPostingId}")
    public ResponseEntity<Void> remove(@PathVariable Long jobPostingId) {
        jobPostingService.remove(jobPostingId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{jobPostingId}/apply")
    public ResponseEntity<ApplyResponse> summit(@PathVariable Long jobPostingId,
                                                @RequestBody ApplyPost post) {
        ApplyResponse response = applyService.save(jobPostingId, post);
        URI uri = URI.create(JOB_POSTING_URI + response.getJobPostingId() + "/apply" + response.getApplyId());
        return ResponseEntity.created(uri).body(response);
    }

}