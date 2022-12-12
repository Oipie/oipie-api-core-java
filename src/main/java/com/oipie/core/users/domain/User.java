package com.oipie.core.users.domain;

import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.shared.domain.Password;
import com.oipie.core.users.domain.primitives.UserPrimitives;

public class User {

    private final UserId userId;

    private final Email email;

    private final String nickname;

    private final Password password;


    private User(UserId userId, Email email, String nickname, Password password) {
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public static User create(UserId userId, Email email, String nickname, Password password) {
        return new User(userId,email,nickname,password);
    }

    public static User fromPrimitives(UserPrimitives userPrimitives) throws DomainError {
        return new User(
                UserId.fromString(userPrimitives.userId()),
                Email.fromString(userPrimitives.email()),
                userPrimitives.nickname(),
                Password.fromString(userPrimitives.password())
        );
    }

    public Password getPassword(){
        return this.password;
    }

    public UserPrimitives toPrimitives() {
        return new UserPrimitives(
                this.userId.toString(),
                this.email.toPrimitives(),
                this.nickname,
                this.password.toPrimitives()
        );
    }

    public UserId getUserId() {
        return this.userId;
    }
}
