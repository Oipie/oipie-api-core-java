package com.oipie.core.recipes.application;

import com.oipie.core.recipes.domain.Recipe;
import com.oipie.core.recipes.domain.RecipeId;
import com.oipie.core.recipes.domain.RecipeRepository;
import com.oipie.core.recipes.domain.errors.RecipeNotFoundError;
import com.oipie.core.shared.domain.DomainError;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeShower {
    private final RecipeRepository recipeRepository;


    public RecipeShower(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe execute(RecipeId recipeId) throws DomainError {
        Optional<Recipe> recipe = this.recipeRepository.findByRecipeId(recipeId);
        return recipe.orElseThrow(() -> new RecipeNotFoundError(recipeId));
    }
}
