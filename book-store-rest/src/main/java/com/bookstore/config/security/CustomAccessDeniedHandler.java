package com.bookstore.config.security;

import com.bookstore.domain.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException {
        PrintWriter out = response.getWriter();
        String errorMessage = new ObjectMapper().writeValueAsString(ErrorResponse.builder().code(-1).message(e.getMessage()).build());
        out.println(errorMessage);
    }
}
