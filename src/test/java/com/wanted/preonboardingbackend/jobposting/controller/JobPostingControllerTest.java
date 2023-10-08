package com.wanted.preonboardingbackend.jobposting.controller;

import com.google.gson.Gson;
import com.wanted.preonboardingbackend.jobposting.dto.*;
import com.wanted.preonboardingbackend.jobposting.service.ApplyService;
import com.wanted.preonboardingbackend.jobposting.service.JobPostingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JobPostingController.class)
class JobPostingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JobPostingService jobPostingService;

    @MockBean
    ApplyService applyService;

    Gson gson = new Gson();

    static final String JOB_POSTING_URI = "/job-postings";

    static final String JOB_POSTING_ID = "/{jobPostingId}";

    static final String APPLY = "/apply";

    @DisplayName("POST: /job-postings")
    @Test
    void write() throws Exception {
        // given
        Long companyId = 1L;
        String position = "백엔드 주니어 개발자";
        Long hiringBonus = 1000000L;
        String skill = "Python";
        String content = "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은...";

        Long jobPostingId = 1L;

        JobPostingPost jobPostingPost = buildJobPostingPost(companyId, position, hiringBonus, skill, content);
        String post = gson.toJson(jobPostingPost);

        JobPostingResponse response = buildJobPostingResponse(jobPostingId, null, null, null, position, hiringBonus, content, skill);

        when(jobPostingService.save(any(JobPostingPost.class))).thenReturn(response);

        // when
        ResultActions actions = mockMvc.perform(post(JOB_POSTING_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(post));

        // then
        actions
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @DisplayName("GET: /job-postings?search=Python")
    @Test
    void getPosts() throws Exception {
        // given
        Long jobPostingId = 1L;
        String name = "원티드랩";
        String country = "대한민국";
        String city = "서울";
        String position = "백엔드 주니어 개발자";
        Long hiringBonus = 1L;
        String content = "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은...";
        String skill = "Python";

        JobPostingResponse jobPostingResponse = buildJobPostingResponse(jobPostingId, name, country, city, position, hiringBonus, content, skill);
        when(jobPostingService.getPosts(anyString())).thenReturn(List.of(jobPostingResponse));

        // when
        ResultActions actions = mockMvc.perform(get(JOB_POSTING_URI)
                .param("search", "Python")
                .accept(MediaType.APPLICATION_JSON));

        // then
        actions
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("PATCH: /job-postings/{jobPostingId}")
    @Test
    void modify() throws Exception {
        Long jobPostingId = 1L;
        String name = "원티드랩";
        String country = "대한민국";
        String city = "서울";
        String position = "백엔드 주니어 개발자";
        Long hiringBonus = 1L;
        String content = "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은...";
        String skill = "Python";

        JobPostingPatch jobPostingPatch = buildJobPostingPatch(position, hiringBonus, skill, content);
        String patch = gson.toJson(jobPostingPatch);

        JobPostingResponse jobPostingResponse = buildJobPostingResponse(jobPostingId, name, country, city, position, hiringBonus, patch, skill);
        when(jobPostingService.update(anyLong(), any(JobPostingPatch.class))).thenReturn(jobPostingResponse);

        ResultActions actions = mockMvc.perform(patch(JOB_POSTING_URI + JOB_POSTING_ID, jobPostingId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(patch));

        actions
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("GET: /job-postings/{jobPostingId}")
    @Test
    void getPost() throws Exception {
        // given
        Long jobPostingId = 1L;
        String name = "원티드랩";
        String country = "대한민국";
        String city = "서울";
        String position = "백엔드 주니어 개발자";
        Long hiringBonus = 1L;
        String content = "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은...";
        String skill = "Python";

        JobPostingDetailResponse jobPostingDetailResponse = buildJobPostingDetailResponse(jobPostingId, name, country, city, position, hiringBonus, content, skill);
        when(jobPostingService.getPost(anyLong())).thenReturn(jobPostingDetailResponse);

        // when
        ResultActions actions = mockMvc.perform(get(JOB_POSTING_URI + JOB_POSTING_ID, jobPostingId)
                .accept(MediaType.APPLICATION_JSON));

        // then
        actions
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("DELETE: /job-postings/{jobPostingId")
    @Test
    void remove() throws Exception {
        // given
        Long jobPostingId = 1L;

        doNothing().when(jobPostingService).remove(anyLong());

        // when
        ResultActions actions = mockMvc.perform(delete(JOB_POSTING_URI + JOB_POSTING_ID, jobPostingId));

        // then
        actions
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @DisplayName("POST: /job-postings/{jobPostingId}/apply")
    @Test
    void summit() throws Exception {
        // given
        Long jobPostingId = 1L;
        Long userId = 1L;
        Long applyId = 1L;

        ApplyPost applyPost = buildApply(userId);
        String post = gson.toJson(applyPost);

        ApplyResponse applyResponse = ApplyResponse.builder().applyId(applyId).userId(userId).jobPostingId(jobPostingId).build();

        when(applyService.save(anyLong(), any(ApplyPost.class))).thenReturn(applyResponse);

        // when
        ResultActions actions = mockMvc.perform(post(JOB_POSTING_URI + JOB_POSTING_ID + APPLY, jobPostingId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(post));

        // then
        actions
                .andDo(print())
                .andExpect(status().isCreated());
    }

    private JobPostingPost buildJobPostingPost(Long companyId, String position, Long hiringBonus, String skill, String content) {
        return JobPostingPost.builder().companyId(companyId).position(position).hiringBonus(hiringBonus).skill(skill).content(content).build();
    }

    private JobPostingPatch buildJobPostingPatch(String position, Long hiringBonus, String skill, String content) {
        return JobPostingPatch.builder().position(position).hiringBonus(hiringBonus).skill(skill).content(content).build();
    }

    private JobPostingResponse buildJobPostingResponse(Long jobPostingId, String companyName, String country, String city, String position, Long hiringBonus, String content, String skill) {
        return JobPostingResponse.builder().jobPostingId(jobPostingId).companyName(companyName).country(country).city(city).position(position).hiringBonus(hiringBonus).content(content).skill(skill).build();
    }

    private JobPostingDetailResponse buildJobPostingDetailResponse(Long jobPostingId, String name, String country, String city, String position, Long hiringBonus, String content, String skill) {
        return JobPostingDetailResponse.builder().jobPostingId(jobPostingId).companyName(name).country(country).city(city).position(position).hiringBonus(hiringBonus).content(content).skill(skill).jobPostings(List.of(1L)).build();
    }

    private ApplyPost buildApply(Long userId) {
        return ApplyPost.builder().userId(userId).build();
    }

}