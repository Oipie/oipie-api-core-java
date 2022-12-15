package com.oipie.core.users.domain;

import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.shared.domain.DomainId;

import java.util.UUID;

public final class UserId extends DomainId {

    private UserId(UUID domainId) {
        super(domainId);
    }

    public static UserId fromString(String domainId) throws DomainError {
        DomainId.isValid(domainId);
        return new UserId(UUID.fromString(domainId));
    }

}
