package com.wanted.preonboardingbackend.jobposting.controller;

import com.wanted.preonboardingbackend.jobposting.dto.*;
import com.wanted.preonboardingbackend.jobposting.service.ApplyService;
import com.wanted.preonboardingbackend.jobposting.service.JobPostingService;
import com.wanted.preonboardingbackend.response.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/job-postings")
@Validated
public class JobPostingController {

    private final JobPostingService jobPostingService;

    private final ApplyService applyService;

    private static final String JOB_POSTING_URI = "/job-postings";

    @PostMapping
    public ResponseEntity<SingleResponse<JobPostingPostResponse>> write(@RequestBody @Validated JobPostingPost post) {
        JobPostingPostResponse response = jobPostingService.save(post);
        URI uri = URI.create(JOB_POSTING_URI);
        return ResponseEntity.created(uri).body(new SingleResponse<>(response));
    }

    @GetMapping
    public ResponseEntity<SingleResponse<List<JobPostingResponse>>> getPosts(@RequestParam(required = false) String search) {
        List<JobPostingResponse> responses = jobPostingService.getPosts(search);
        return ResponseEntity.ok(new SingleResponse<>(responses));
    }

    @PatchMapping("/{jobPostingId}")
    public ResponseEntity<SingleResponse<JobPostingResponse>> modify(@PathVariable @Positive Long jobPostingId,
                                                     @RequestBody @Validated JobPostingPatch patch) {
        JobPostingResponse response = jobPostingService.update(jobPostingId, patch);
        return ResponseEntity.ok(new SingleResponse<>(response));
    }

    @GetMapping("/{jobPostingId}")
    public ResponseEntity<SingleResponse<JobPostingDetailResponse>> getPost(@PathVariable @Positive Long jobPostingId) {
        JobPostingDetailResponse response = jobPostingService.getPost(jobPostingId);
        return ResponseEntity.ok(new SingleResponse<>(response));
    }

    @DeleteMapping("/{jobPostingId}")
    public ResponseEntity<Void> remove(@PathVariable @Positive Long jobPostingId) {
        jobPostingService.remove(jobPostingId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{jobPostingId}/apply")
    public ResponseEntity<SingleResponse<ApplyResponse>> summit(@PathVariable @Positive Long jobPostingId,
                                                @RequestBody @Validated ApplyPost post) {
        ApplyResponse response = applyService.save(jobPostingId, post);
        URI uri = URI.create(JOB_POSTING_URI + response.getJobPostingId() + "/apply" + response.getApplyId());
        return ResponseEntity.created(uri).body(new SingleResponse<>(response));
    }

}