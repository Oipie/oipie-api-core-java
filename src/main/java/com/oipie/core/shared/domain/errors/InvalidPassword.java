package com.oipie.core.shared.domain.errors;

import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.shared.domain.DomainErrorCode;

public class InvalidPassword extends DomainError {
    public InvalidPassword() {
        super(DomainErrorCode.INVALID_PASSWORD, "The password is not strong enough");
    }
}
