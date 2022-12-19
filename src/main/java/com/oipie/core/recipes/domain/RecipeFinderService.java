package com.oipie.core.recipes.domain;

import com.oipie.core.recipes.domain.errors.RecipeNotFoundError;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeFinderService {

    private final RecipeRepository recipeRepository;


    public RecipeFinderService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe findByRecipeId(RecipeId recipeId) {
        Optional<Recipe> recipe = this.recipeRepository.findByRecipeId(recipeId);
        return recipe.orElseThrow(() -> new RecipeNotFoundError(recipeId));
    }
}
