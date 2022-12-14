package com.oipie.core.shared.domain;

import com.oipie.core.shared.domain.ddd.ValueObject;

public final class Password extends ValueObject {

    private static final int MINIMUM_LENGTH = 10;

    private final String password;

    private Password(String password) {
        this.password = password;
    }

    public static boolean isValid(String rawPassword) {
        return rawPassword.length() >= Password.MINIMUM_LENGTH;
    }

    public static Password fromString(String password) {
        return new Password(password);
    }

    public String toPrimitives() {
        return this.password;
    }
}
