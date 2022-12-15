package com.oipie.core.shared.infrastructure.identification;

import com.oipie.core.shared.domain.IdentificationService;

import java.util.UUID;

public final class IdentificationServiceVanilla implements IdentificationService {
    @Override
    public String generateID() {
        return UUID.randomUUID().toString();
    }
}
