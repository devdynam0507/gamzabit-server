package com.gamzabit.api.infrastructure.security.arguments;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.gamzabit.api.infrastructure.security.dto.AuthenticatedUser;
import com.gamzabit.domain.user.UserId;
import com.gamzabit.domain.user.UserReader;
import com.gamzabit.domain.user.vo.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticatedUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final UserReader userReader;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return AuthenticatedUser.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(
        MethodParameter parameter,
        ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest,
        WebDataBinderFactory binderFactory
    ) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return new AuthenticatedUser(null);
        }
        UserId id = (UserId) authentication.getPrincipal();
        User user = userReader.findUserById(id.getId());

        return new AuthenticatedUser(user);
    }
}
