package com.bookstore.config.security.filter;

import com.bookstore.domain.CustomerDomain;
import com.bookstore.domain.ErrorResponse;
import com.bookstore.exception.JwtTokenException;
import com.bookstore.exception.JwtTokenMissingException;
import com.bookstore.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthorizationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        try {
            if (header == null || !header.startsWith("Bearer "))
                throw new JwtTokenMissingException("No JWT token found in request headers");

            String authToken = header.substring(7);
            CustomerDomain parsedUser = jwtUtil.parsedToken(authToken);

            if (parsedUser == null) {
                SecurityContextHolder.clearContext();
                throw new JwtTokenException("Jwt token is not valid");
            }

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(parsedUser.getUsername(), parsedUser.getId(), new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(token);
            chain.doFilter(request, response);

        } catch (RuntimeException ex) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .code(-1).message(ex.getMessage())
                    .build();

            response.setContentType("application/json");
            response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
        }

    }
}
