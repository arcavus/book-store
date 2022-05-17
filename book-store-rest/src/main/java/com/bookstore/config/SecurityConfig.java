package com.bookstore.config;

import com.bookstore.config.security.CustomAccessDeniedHandler;
import com.bookstore.config.security.JwtAuthenticationFailureHandler;
import com.bookstore.config.security.filter.JwtAuthenticationFilter;
import com.bookstore.config.security.filter.JwtAuthorizationFilter;
import com.bookstore.service.impl.CustomerServiceImpl;
import com.bookstore.util.Helper;
import com.bookstore.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtUtil jwtUtil;
    private final CustomerServiceImpl customerService;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return Helper.getEncryption();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/v2/api-docs", "/configuration/ui",
                "/swagger-resources/**", "/configuration/**", "/swagger-ui.html",
                "/resources/**",
                "/webjars/**",
                "/**/customers/register");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll();
        http.csrf().disable().cors()
                .and().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .and().authorizeRequests().antMatchers(HttpMethod.POST, "/**/customers/register").permitAll()
//                .anyRequest().permitAll();
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler());
    }

    private JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager(), jwtUtil, customerService);
        jwtAuthenticationFilter.setAuthenticationFailureHandler(new JwtAuthenticationFailureHandler());

        return jwtAuthenticationFilter;
    }

    private JwtAuthorizationFilter jwtAuthorizationFilter() throws Exception {
        return new JwtAuthorizationFilter(jwtUtil);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerService).passwordEncoder(passwordEncoder());
    }

    private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/configuration/**",
            "/webjars/**",
            "/h2-console/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html/**"
    };
}
