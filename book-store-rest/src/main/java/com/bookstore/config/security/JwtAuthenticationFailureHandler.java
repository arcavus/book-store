package com.bookstore.config.security;

import com.bookstore.domain.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        PrintWriter out = response.getWriter();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        out.println(new ObjectMapper().writeValueAsString(ErrorResponse.builder().code(-1).message(e.getMessage()).build()));
    }
}
