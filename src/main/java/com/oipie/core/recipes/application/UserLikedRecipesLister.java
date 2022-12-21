package com.oipie.core.recipes.application;

import com.oipie.core.recipes.domain.Recipe;
import com.oipie.core.recipes.domain.RecipeRepository;
import com.oipie.core.users.domain.UserId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLikedRecipesLister {

    private final RecipeRepository recipeRepository;


    public UserLikedRecipesLister(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> execute(UserId userId) {
        return this.recipeRepository.getUserLikedRecipes(userId);
    }
}
