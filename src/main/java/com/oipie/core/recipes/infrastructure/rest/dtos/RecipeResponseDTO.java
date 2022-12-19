package com.oipie.core.recipes.infrastructure.rest.dtos;

import com.oipie.core.recipes.domain.Recipe;
import com.oipie.core.recipes.domain.primitives.RecipePrimitives;

public record RecipeResponseDTO(
        String id,
        String name,
        int preparationTime,
        String cover,
        long likesAmount) {

    public static RecipeResponseDTO fromDomain(Recipe recipe) {
        RecipePrimitives recipePrimitives = recipe.toPrimitives();
        long likesAmount = recipe.getLikesAmount();

        return new RecipeResponseDTO(
                recipePrimitives.id(),
                recipePrimitives.name(),
                recipePrimitives.preparationTime(),
                recipePrimitives.cover(),
                likesAmount);
    }
}
