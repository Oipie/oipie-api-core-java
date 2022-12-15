package com.oipie.core.recipes.infrastructure.rest.dtos;

import com.oipie.core.recipes.domain.Recipe;
import com.oipie.core.recipes.domain.primitives.RecipePrimitives;

public record RecipeResponseDTO(
        String id,
        String name,
        int preparationTime,
        String cover) {

    public static RecipeResponseDTO fromDomain(Recipe recipe) {
        RecipePrimitives recipePrimitives = recipe.toPrimitives();

        return new RecipeResponseDTO(
                recipePrimitives.id(),
                recipePrimitives.name(),
                recipePrimitives.preparationTime(),
                recipePrimitives.cover());
    }
}
