package com.oipie.core.shared.domain.errors;

import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.shared.domain.DomainErrorCode;

public class InvalidJwt extends DomainError {

    public InvalidJwt() {
        super(DomainErrorCode.INVALID_JWT, "The jwt is not valid");
    }
}
