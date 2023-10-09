package com.wanted.preonboardingbackend.error.controller;

import com.wanted.preonboardingbackend.exception.ExceptionCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ErrorController {

    @GetMapping("/error-code")
    public Map<String, ErrorCodeResponse> findErrors() {
        Map<String, ErrorCodeResponse> map = new HashMap<>();

        for (ExceptionCode exceptionCode : ExceptionCode.values()) {
            map.put(exceptionCode.name(), new ErrorCodeResponse(exceptionCode));
        }

        return map;
    }

    public static class ErrorCodeResponse {

        private String name;

        private String message;

        private int status;

        public ErrorCodeResponse(ExceptionCode exceptionCode) {
            this.name = exceptionCode.getMessage();
            this.message = exceptionCode.name();
            this.status = exceptionCode.getStatus();
        }

        public String getName() {
            return name;
        }

        public String getMessage() {
            return message;
        }

        public int getStatus() {
            return status;
        }

        public ErrorCodeResponse() {
        }
    }

}
