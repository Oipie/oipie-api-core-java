package com.oipie.core.shared.domain.errors;

import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.shared.domain.DomainErrorCode;

public class InvalidId extends DomainError {

    public InvalidId(String id) {
        super(DomainErrorCode.INVALID_ID, "The id " + id + " is not valid");
    }
}
