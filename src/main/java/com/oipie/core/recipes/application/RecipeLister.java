package com.oipie.core.recipes.application;

import com.oipie.core.recipes.domain.Recipe;
import com.oipie.core.recipes.domain.RecipeRepository;
import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.shared.domain.PageResult;
import org.springframework.stereotype.Service;

@Service
public class RecipeLister {

    private final RecipeRepository recipeRepository;


    public RecipeLister(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public PageResult<Recipe> execute(int page, int limit) throws DomainError {
        PageResult<Recipe> recipes = this.recipeRepository.find(page, limit);

        return recipes;
    }
}
