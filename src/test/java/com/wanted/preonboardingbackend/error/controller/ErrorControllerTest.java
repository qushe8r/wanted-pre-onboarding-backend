package com.wanted.preonboardingbackend.error.controller;

import com.wanted.preonboardingbackend.exception.ExceptionCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.snippet.Attributes.key;

@WebMvcTest(ErrorController.class)
@AutoConfigureRestDocs
public class ErrorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void errorCodes() throws Exception {

        ResultActions actions = mockMvc.perform(get("/error-code")
                .contentType(MediaType.APPLICATION_JSON));

        actions
                .andDo(document("error-code",
                        customResponseFields("error-code", fieldDescriptors())
                ));
    }

    private List<FieldDescriptor> fieldDescriptors() {
        List<FieldDescriptor> fieldDescriptors = new ArrayList<>();

        for (ExceptionCode exceptionCode : ExceptionCode.values()) {
            FieldDescriptor attributes =
                    fieldWithPath(exceptionCode.name()).type(JsonFieldType.OBJECT)
                            .attributes(
                                    key("businessExceptionCode").value(exceptionCode.getBusinessExceptionCode()),
                                    key("statusCode").value(String.valueOf(exceptionCode.getStatus())),
                                    key("message").value(exceptionCode.getMessage())
                            );
            fieldDescriptors.add(attributes);
        }

        return fieldDescriptors;
    }

    public static CustomResponseFiledsSnippet customResponseFields(String snippetFilePrefix, List<FieldDescriptor> fieldDescriptors) {
        return new CustomResponseFiledsSnippet(snippetFilePrefix, fieldDescriptors, true);
    }

}
