package com.oipie.core.recipes.domain;

import com.oipie.core.recipes.domain.primitives.RecipePrimitives;
import com.oipie.core.shared.domain.ddd.Aggregate;

public final class Recipe extends Aggregate {

    private final RecipeId recipeId;

    private final String name;

    private final int preparationTime;

    private final String cover;

    private Recipe(RecipeId recipeId, String name, int preparationTime, String cover) {
        this.recipeId = recipeId;
        this.name = name;
        this.preparationTime = preparationTime;
        this.cover = cover;
    }

    public static Recipe create(RecipeId recipeId, String name, int preparationTime, String cover) {
        return new Recipe(recipeId, name, preparationTime, cover);
    }

    public static Recipe fromPrimitives(RecipePrimitives recipePrimitives) {
        return new Recipe(
                RecipeId.fromString(recipePrimitives.id()),
                recipePrimitives.name(),
                recipePrimitives.preparationTime(),
                recipePrimitives.cover()
        );
    }

    public RecipePrimitives toPrimitives() {
        return new RecipePrimitives(
                this.recipeId.toString(),
                this.name,
                this.preparationTime,
                this.cover
        );
    }
}
