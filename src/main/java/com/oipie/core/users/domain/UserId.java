package com.oipie.core.users.domain;

import com.oipie.core.shared.domain.DomainId;

import java.util.UUID;

public class UserId extends DomainId {

    private UserId(UUID domainId) {
        super(domainId);
    }

    public static UserId fromString(String domainId) {
        return new UserId(UUID.fromString(domainId));
    }

}
