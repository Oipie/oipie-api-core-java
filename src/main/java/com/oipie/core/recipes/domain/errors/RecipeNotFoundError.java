package com.oipie.core.recipes.domain.errors;

import com.oipie.core.recipes.domain.RecipeId;
import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.shared.domain.DomainErrorCode;

public class RecipeNotFoundError extends DomainError {

    public RecipeNotFoundError(RecipeId recipeId) {
        super(DomainErrorCode.RECIPE_NOT_FOUND, "The recipe with " + recipeId.toString() + " not found");
    }
}
