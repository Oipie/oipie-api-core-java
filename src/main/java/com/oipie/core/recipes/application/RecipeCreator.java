package com.oipie.core.recipes.application;

import com.oipie.core.recipes.domain.Recipe;
import com.oipie.core.recipes.domain.RecipeId;
import com.oipie.core.recipes.domain.RecipeRepository;
import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.shared.domain.IdentificationService;
import org.springframework.stereotype.Service;

@Service
public class RecipeCreator {

    private final IdentificationService identificationService;

    private final RecipeRepository recipeRepository;


    public RecipeCreator(IdentificationService identificationService, RecipeRepository recipeRepository) {
        this.identificationService = identificationService;
        this.recipeRepository = recipeRepository;
    }


    public Recipe execute(String name, int preparationTime, String cover) throws DomainError {
        Recipe recipe = Recipe.create(RecipeId.fromString(this.identificationService.generateID()), name, preparationTime, cover);

        this.recipeRepository.save(recipe);

        return recipe;
    }
}
