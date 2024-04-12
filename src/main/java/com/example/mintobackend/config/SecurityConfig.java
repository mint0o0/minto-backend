package com.example.mintobackend.config;

import com.example.mintobackend.jwt.JwtAccessDeniedHandler;
import com.example.mintobackend.jwt.JwtAuthenticationEntryPoint;
import com.example.mintobackend.jwt.JwtFilter;
import com.example.mintobackend.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig{
    private final TokenProvider tokenProvider;
    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtFilter jwtFilter;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                    .exceptionHandling((exception)->exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                    .exceptionHandling((exception)->exception.accessDeniedHandler(jwtAccessDeniedHandler))
                    .headers((headersConfig)->headersConfig.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                    .sessionManagement((session)->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests((authorizeRequest)
                            ->authorizeRequest
                            .requestMatchers("/auth/**").permitAll()
                            .requestMatchers("/festival/**").permitAll()
                            .anyRequest().authenticated());

        return http.build();
    }

}