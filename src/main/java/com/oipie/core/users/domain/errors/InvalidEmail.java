package com.oipie.core.users.domain.errors;

import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.shared.domain.DomainErrorCode;

public class InvalidEmail extends DomainError {
    public InvalidEmail() {
        super(DomainErrorCode.INVALID_EMAIL, "The Email you provided is not a valid one");
    }
}
