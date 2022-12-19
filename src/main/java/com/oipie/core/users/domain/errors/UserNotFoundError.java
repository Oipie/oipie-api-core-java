package com.oipie.core.users.domain.errors;

import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.shared.domain.DomainErrorCode;
import com.oipie.core.users.domain.UserId;

public class UserNotFoundError extends DomainError {

    public UserNotFoundError(UserId userId) {
        super(DomainErrorCode.USER_NOT_FOUND, "The user with id " + userId.toString() + " not found");
    }
}
