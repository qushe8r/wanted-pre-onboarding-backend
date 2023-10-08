package com.wanted.preonboardingbackend.user.controller;

import com.wanted.preonboardingbackend.user.dto.UserResponse;
import com.wanted.preonboardingbackend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private static final String USER_API_URI = "/users";

    private final UserService userService;

    @PostMapping
    ResponseEntity<UserResponse> register() {
        UserResponse response = userService.save();
        URI uri = URI.create(USER_API_URI + response.getUserId());
        return ResponseEntity.created(uri).body(response);
    }

}
