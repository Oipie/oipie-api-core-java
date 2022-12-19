package com.oipie.core.recipes.application;

import com.oipie.core.recipes.domain.Recipe;
import com.oipie.core.recipes.domain.RecipeFinderService;
import com.oipie.core.recipes.domain.RecipeId;
import com.oipie.core.recipes.domain.RecipeRepository;
import com.oipie.core.users.domain.User;
import com.oipie.core.users.domain.UserFinderService;
import com.oipie.core.users.domain.UserId;
import org.springframework.stereotype.Service;

@Service
public class RecipeLiker {

    private final RecipeRepository recipeRepository;

    private final RecipeFinderService recipeFinderService;

    private final UserFinderService userFinderService;


    public RecipeLiker(RecipeRepository recipeRepository, RecipeFinderService recipeFinderService, UserFinderService userFinderService) {
        this.recipeRepository = recipeRepository;
        this.recipeFinderService = recipeFinderService;
        this.userFinderService = userFinderService;
    }

    public void execute(RecipeId recipeId, UserId userId) {
        Recipe recipe = this.recipeFinderService.findByRecipeId(recipeId);
        User user = this.userFinderService.findByUserId(userId);

        recipe.addLike(user);

        this.recipeRepository.save(recipe);
    }
}
