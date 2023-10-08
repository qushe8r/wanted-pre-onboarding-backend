package com.wanted.preonboardingbackend.company.controller;

import com.google.gson.Gson;
import com.wanted.preonboardingbackend.company.dto.CompanyPost;
import com.wanted.preonboardingbackend.company.dto.CompanyResponse;
import com.wanted.preonboardingbackend.company.service.CompanyService;
import org.junit.jupiter.api.DisplayName;
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

@WebMvcTest(CompanyController.class)
class CompanyControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CompanyService companyService;

    Gson gson = new Gson();

    static final String COMPANY_URI = "/companies";

    @DisplayName("POST: /companies")
    @Test
    void register() throws Exception {
        // given
        Long companyId = 1L;
        String name = "원티드랩";
        String country = "대한민국";
        String city = "서울";

        CompanyPost companyPost = buildCompanyPost(name, country, city);
        String post = gson.toJson(companyPost);

        CompanyResponse companyResponse = buildCompanyResponse(companyId, name, country, city);

        when(companyService.save(any(CompanyPost.class))).thenReturn(companyResponse);

        // when
        ResultActions actions = mockMvc.perform(post(COMPANY_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(post));

        // then
        actions
                .andDo(print())
                .andExpect(status().isCreated());
    }

    private CompanyPost buildCompanyPost(String name, String country, String city) {
        return CompanyPost.builder().name(name).country(country).city(city).build();
    }

    private CompanyResponse buildCompanyResponse(Long companyId, String name, String country, String city) {
        return CompanyResponse.builder().companyId(companyId).companyName(name).country(country).city(city).build();
    }

}