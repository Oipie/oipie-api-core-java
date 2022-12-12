package com.oipie.core.users.fixtures;

import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.users.domain.User;
import com.oipie.core.users.domain.primitives.UserPrimitives;

public class UserFixture {


    public static final UserPrimitives LUIGI_PRIMITIVES = new UserPrimitives(
            "4a7c6cc0-963b-4109-b856-3a8177529388",
            "luigi@acidtango.com",
            "luigi",
            "fake_password"
    );

    public static User luigi() throws DomainError {
        return User.fromPrimitives(LUIGI_PRIMITIVES);
    }
}
