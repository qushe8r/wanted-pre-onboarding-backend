package com.wanted.preonboardingbackend.jobposting.controller;

import com.google.gson.Gson;
import com.wanted.preonboardingbackend.jobposting.dto.JobPostingPost;
import com.wanted.preonboardingbackend.jobposting.dto.JobPostingResponse;
import com.wanted.preonboardingbackend.jobposting.service.JobPostingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JobPostingController.class)
class JobPostingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JobPostingService jobPostingService;

    Gson gson = new Gson();

    static final String JOB_POSTING_URI = "/job-postings";

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

    private JobPostingPost buildJobPostingPost(Long companyId, String position, Long hiringBonus, String skill, String content) {
        return JobPostingPost.builder().companyId(companyId).position(position).hiringBonus(hiringBonus).skill(skill).content(content).build();
    }

    private JobPostingResponse buildJobPostingResponse(Long jobPostingId, String companyName, String country, String city, String position, Long hiringBonus, String content, String skill) {
        return JobPostingResponse.builder().jobPostingId(jobPostingId).companyName(companyName).country(country).city(city).position(position).hiringBonus(hiringBonus).content(content).skill(skill).build();
    }

}