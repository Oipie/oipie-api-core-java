package com.oipie.core.recipes.domain;

import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.shared.domain.PageResult;
import com.oipie.core.users.domain.UserId;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository {
    void save(Recipe recipe);

    Optional<Recipe> findByRecipeId(RecipeId recipeId) throws DomainError;

    PageResult<Recipe> find(int page, int limit) throws DomainError;

    List<Recipe> getUserLikedRecipes(UserId userId);
}
