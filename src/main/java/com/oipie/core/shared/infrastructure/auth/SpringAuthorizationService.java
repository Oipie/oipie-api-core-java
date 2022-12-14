package com.oipie.core.shared.infrastructure.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.oipie.core.shared.domain.AuthorizationService;
import com.oipie.core.shared.domain.Password;
import com.oipie.core.users.domain.UserId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.Date;

public class SpringAuthorizationService implements AuthorizationService {

    private static final int PASSWORD_STRENGTH = 10;

    private static final String DEFAULT_ISSUER = "Oipie";
    private static final long DEFAULT_EXPIRE_TIME = 1000 * 3600 * 24 * 7;
    @Value("${authorization.jwt.secret}")
    private String jwtSecretKey;

    @Override
    public Password hashPassword(String password) {

        String encodedPassword = new BCryptPasswordEncoder(PASSWORD_STRENGTH, new SecureRandom()).encode(password);
        return Password.fromString(encodedPassword);
    }

    @Override
    public boolean verifyPassword(Password password, String attempt) {
        return new BCryptPasswordEncoder(PASSWORD_STRENGTH, new SecureRandom())
                .matches(attempt, password.toPrimitives());
    }

    @Override
    public String createJWT(UserId userId) {
        Date expireTime = new Date(System.currentTimeMillis() + DEFAULT_EXPIRE_TIME);

        return JWT.create()
                .withIssuer(DEFAULT_ISSUER)
                .withExpiresAt(expireTime)
                .withSubject(userId.toString())
                .sign(Algorithm.HMAC512(jwtSecretKey));
    }

    @Override
    public boolean verifyJWT(String jwt) {
        try {
            JWT.require(Algorithm.HMAC512(jwtSecretKey))
                    .withIssuer(DEFAULT_ISSUER)
                    .build()
                    .verify(jwt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
