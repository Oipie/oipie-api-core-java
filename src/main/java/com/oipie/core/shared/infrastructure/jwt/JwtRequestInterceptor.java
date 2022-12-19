package com.oipie.core.shared.infrastructure.jwt;

import com.oipie.core.shared.domain.AuthorizationService;
import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.shared.domain.errors.InvalidJwt;
import com.oipie.core.shared.infrastructure.annotations.RolesAllowed;
import com.oipie.core.shared.infrastructure.auth.Roles;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtRequestInterceptor implements HandlerInterceptor {

    private AuthorizationService authorizationService;

    public JwtRequestInterceptor(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws DomainError {
        final RolesAllowed roleAnnotation = ((HandlerMethod) handler).getMethod().getAnnotation((RolesAllowed.class));

        if (roleAnnotation == null) return true;

        final Roles[] roles = roleAnnotation.value();

        if (roles.length == 0) return true;

        final String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) throw new InvalidJwt();

        final int jwtBeginIndex = 7;

        String jwt = authorizationHeader.substring(jwtBeginIndex);

        if (!this.authorizationService.verifyJWT(jwt, roles)) throw new InvalidJwt();

        return true;
    }
}
