package com.gamzabit.api.infrastructure.security.filters;

import java.util.Collections;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import com.gamzabit.api.infrastructure.security.jwt.JwtProvider;
import com.gamzabit.api.infrastructure.security.jwt.JwtStrategy;
import com.gamzabit.api.infrastructure.security.jwt.JwtTokenType;
import com.gamzabit.api.infrastructure.security.roles.Roles;
import com.gamzabit.api.user.exception.UserNotFoundException;
import com.gamzabit.api.user.service.UserService;
import com.gamzabit.api.user.service.vo.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PreJwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtStrategy jwtStrategy;
    private final UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass())) {
            throw new TokenMissingException("invalid token");
        }
        String token = authentication.getPrincipal().toString();
        JwtProvider jwtProvider = jwtStrategy.getProvider(JwtTokenType.Access);
        Long userId = jwtProvider.decrypt(token, "id", Long.class);
        User user;
        try {
            user = userService.findUserById(userId);
        } catch (UserNotFoundException e) {
            throw new TokenMissingException(e);
        }
        return new PreAuthenticatedAuthenticationToken(
            user.id(),
            "",
            Collections.singletonList(new SimpleGrantedAuthority(Roles.Member.name()))
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
