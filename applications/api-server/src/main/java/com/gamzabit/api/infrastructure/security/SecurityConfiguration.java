package com.gamzabit.api.infrastructure.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import com.gamzabit.api.infrastructure.security.filters.PreJwtAuthenticationFilter;
import com.gamzabit.api.infrastructure.security.filters.PreJwtAuthenticationProvider;
import com.gamzabit.api.infrastructure.security.jwt.JwtStrategy;
import com.gamzabit.api.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@ConditionalOnDefaultWebSecurity
@RequiredArgsConstructor
@ConditionalOnWebApplication(type = Type.SERVLET)
public class SecurityConfiguration {

    private final UserService userService;
    private final JwtStrategy jwtStrategy;

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> {
            authorize
                .requestMatchers("/api/v1/order").authenticated()
                .anyRequest().permitAll();
        });
        http.csrf(csrf -> {
            csrf.ignoringRequestMatchers("/h2-console/**");
            csrf.disable();
        });
        http.headers(headers -> {
            headers.frameOptions(FrameOptionsConfig::sameOrigin);
        });
        http.sessionManagement(sessionManagement -> {
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });
        http.addFilterAt(tokenPreAuthFilter(), AbstractPreAuthenticatedProcessingFilter.class);

        return http.build();
    }

    @Bean
    public PreJwtAuthenticationFilter tokenPreAuthFilter() {
        PreJwtAuthenticationFilter tokenPreAuthFilter = new PreJwtAuthenticationFilter();
        ProviderManager providerManager = new ProviderManager(preAuthTokenProvider());
        tokenPreAuthFilter.setAuthenticationManager(providerManager);

        return tokenPreAuthFilter;
    }

    @Bean
    public PreJwtAuthenticationProvider preAuthTokenProvider() {
        return new PreJwtAuthenticationProvider(jwtStrategy, userService);
    }
}
