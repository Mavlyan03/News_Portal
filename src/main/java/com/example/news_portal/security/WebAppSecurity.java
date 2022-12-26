package com.example.news_portal.security;

import com.example.news_portal.security.jwt.JwtTokenVerifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class WebAppSecurity {

    private final JwtTokenVerifier verifier;

    public WebAppSecurity(JwtTokenVerifier verifier) {
        this.verifier = verifier;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf().disable()
                .authorizeRequests(
                        (auth) -> auth.antMatchers("/swagger",
                                        "v3/api-docs/**",
                                        "/swagger-ui/index.html").permitAll()
                                .anyRequest().permitAll());
        http.addFilterBefore(verifier, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
