package com.example.news_portal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class WebAppSecurity {

//    private final JwtTokenVerifier verifier;
//
//    public WebAppSecurity(JwtTokenVerifier verifier) {
//        this.verifier = verifier;
//    }
//
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
