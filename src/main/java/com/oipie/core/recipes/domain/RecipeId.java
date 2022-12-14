package com.oipie.core.recipes.domain;

import com.oipie.core.shared.domain.DomainId;

import java.util.UUID;

public class RecipeId extends DomainId {
    private RecipeId(UUID domainId) {
        super(domainId);
    }

    public static RecipeId fromString(String domainId) {
        return new RecipeId(UUID.fromString(domainId));
    }

}
