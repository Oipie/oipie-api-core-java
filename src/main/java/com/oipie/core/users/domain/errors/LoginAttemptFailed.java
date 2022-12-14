package com.oipie.core.users.domain.errors;

import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.shared.domain.DomainErrorCode;

public class LoginAttemptFailed extends  DomainError {

        public LoginAttemptFailed() {
            super(DomainErrorCode.LOGIN_FAILED, "Login failed");
        }
}


