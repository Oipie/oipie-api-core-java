package com.oipie.core.shared.domain;

import com.oipie.core.shared.infrastructure.auth.Roles;
import com.oipie.core.users.domain.UserId;

public interface AuthorizationService {


    Password hashPassword(String password);

    boolean verifyPassword(Password password, String attempt);

    String createUserJWT(UserId userId);

    boolean verifyJWT(String jwt, Roles[] roles);
}


