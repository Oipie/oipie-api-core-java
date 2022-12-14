package com.oipie.core.recipes.infrastructure.rest;

import com.oipie.core.recipes.application.RecipeCreator;
import com.oipie.core.recipes.domain.Recipe;
import com.oipie.core.recipes.infrastructure.rest.dtos.CreateRecipeRequestDTO;
import com.oipie.core.recipes.infrastructure.rest.dtos.RecipeResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/recipes")
public class RecipesController {

    private final RecipeCreator recipeCreator;

    public RecipesController(RecipeCreator recipeCreator) {
        this.recipeCreator = recipeCreator;
    }


    @Transactional
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public RecipeResponseDTO createRecipe(@Valid @RequestBody CreateRecipeRequestDTO requestDTO) {

        Recipe recipe = this.recipeCreator.execute(requestDTO.name(), requestDTO.preparationTime(), requestDTO.cover());

        return RecipeResponseDTO.fromDomain(recipe);

    }
}
