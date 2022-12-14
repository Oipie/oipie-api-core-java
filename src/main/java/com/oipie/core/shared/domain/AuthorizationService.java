package com.oipie.core.shared.domain;

import com.oipie.core.users.domain.UserId;

public interface AuthorizationService {


    Password hashPassword(String password);

    boolean verifyPassword(Password password, String attempt);

    String createJWT(UserId userId);

    boolean verifyJWT(String jwt);
}


