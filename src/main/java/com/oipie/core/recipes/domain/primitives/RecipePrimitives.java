package com.oipie.core.recipes.domain.primitives;

public record RecipePrimitives(
        String id,
        String name,
        int preparationTime,
        String cover,
        LikesPrimitives likes) {
}
