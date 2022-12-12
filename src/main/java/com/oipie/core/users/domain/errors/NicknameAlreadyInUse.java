package com.oipie.core.users.domain.errors;

import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.shared.domain.DomainErrorCode;

public class NicknameAlreadyInUse extends DomainError {
    public NicknameAlreadyInUse(String nickname) {
        super(DomainErrorCode.NICKNAME_ALREADY_IN_USE, "The nickname " + nickname + " is already registered in the system");
    }
}
