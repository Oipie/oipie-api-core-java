package com.oipie.core.users.domain.errors;

import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.shared.domain.DomainErrorCode;
import com.oipie.core.users.domain.Email;

public class EmailAlreadyInUse extends DomainError {
    public EmailAlreadyInUse(Email email) {
        super(DomainErrorCode.EMAIL_ALREADY_IN_USE, "The Email " + email.toPrimitives() + " is already registered in the system");
    }
}
