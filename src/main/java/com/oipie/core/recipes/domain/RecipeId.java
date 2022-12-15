package com.oipie.core.recipes.domain;

import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.shared.domain.DomainId;

import java.util.UUID;

public final class RecipeId extends DomainId {
    private RecipeId(UUID domainId) {
        super(domainId);
    }

    public static RecipeId fromString(String domainId) throws DomainError {
        DomainId.isValid(domainId);
        return new RecipeId(UUID.fromString(domainId));
    }

}
