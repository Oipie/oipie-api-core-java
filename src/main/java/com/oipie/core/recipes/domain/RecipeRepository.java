package com.oipie.core.recipes.domain;

import java.util.Optional;

public interface RecipeRepository {
    void save(Recipe recipe);

    Optional<Recipe> findByRecipeId(RecipeId recipeId);
}
