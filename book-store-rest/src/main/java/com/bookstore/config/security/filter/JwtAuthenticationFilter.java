package com.bookstore.config.security.filter;

import com.bookstore.domain.LoginRequestDto;
import com.bookstore.enums.ErrorCodeEnum;
import com.bookstore.exception.CustomRuntimeException;
import com.bookstore.config.security.CustomerPrincipal;
import com.bookstore.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtil jwtUtil;
    private UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        setFilterProcessesUrl("/api/v1/customers/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequestDto credential = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credential.getUsername(),
                    credential.getPassword(), new ArrayList<>());

            return getAuthenticationManager().authenticate(token);
        } catch (IOException e) {
            throw new CustomRuntimeException(ErrorCodeEnum.UN_AUTHORIZATION);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        UserDetails principal = ((UserDetails) authResult.getPrincipal());
        CustomerPrincipal customer = (CustomerPrincipal) userDetailsService.loadUserByUsername(principal.getUsername());

        String token = jwtUtil.generateToken(customer);

        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
