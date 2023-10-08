package com.wanted.preonboardingbackend.user.controller;

import com.wanted.preonboardingbackend.user.dto.UserResponse;
import com.wanted.preonboardingbackend.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    static final String USER_API_URI = "/users";

    @Test
    void register() throws Exception {
        // given
        Long userId = 1L;
        UserResponse userResponse = UserResponse.builder().userId(userId).build();

        when(userService.save()).thenReturn(userResponse);

        // when
        ResultActions actions = mockMvc.perform(post(USER_API_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions
                .andDo(print())
                .andExpect(status().isCreated());
    }

}