package com.gamzabit.api.infrastructure.security.arguments;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gamzabit.domain.user.UserReader;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AuthenticatedUserArgumentResolverConfiguration implements WebMvcConfigurer {

    private final UserReader userReader;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AuthenticatedUserArgumentResolver(userReader));
    }
}
