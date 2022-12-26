package com.example.news_portal.security.jwt;

import com.example.news_portal.entity.User;
import com.example.news_portal.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtTokenVerifier extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        Optional<String> optional = getJWTFromRequest(request);
        optional.ifPresent(token -> {
                    String name = jwtTokenUtil.validateJWTToken(token);
                    User user = userRepository.findByName(name).orElseThrow(
                            () -> new UsernameNotFoundException(String.format("User with name %s not found", name)));
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            user.getAuthorities()
                    );

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
        );

        filterChain.doFilter(request, response);
    }

    private Optional<String> getJWTFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER)) {
            return Optional.of(bearerToken.substring(BEARER.length()));
        }

        return Optional.empty();
    }
}
