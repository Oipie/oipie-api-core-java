package com.oipie.core.shared.infrastructure.identification;

import com.oipie.core.shared.domain.IDService;

import java.util.UUID;

public final class IDServiceVanilla implements IDService {
    @Override
    public String generateID() {
        return UUID.randomUUID().toString();
    }
}
