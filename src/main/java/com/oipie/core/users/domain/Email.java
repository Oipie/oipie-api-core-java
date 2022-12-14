package com.oipie.core.users.domain;

import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.shared.domain.ddd.ValueObject;
import com.oipie.core.users.domain.errors.InvalidEmail;

import java.util.regex.Pattern;

public final class Email extends ValueObject {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    private final String email;

    private Email(String email) {
        this.email = email;
    }

    public static Email fromString(String email) throws DomainError {
        boolean validEmail = Pattern.compile(Email.EMAIL_REGEX).matcher(email).matches();
        if (!validEmail) throw new InvalidEmail();

        return new Email(email);
    }

    public String toPrimitives() {
        return this.email;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Email that)) return false;
        return this.email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return this.email.hashCode();
    }
}
