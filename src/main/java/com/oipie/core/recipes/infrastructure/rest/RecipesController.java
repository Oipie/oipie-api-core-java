package com.oipie.core.recipes.infrastructure.rest;

import com.oipie.core.recipes.application.RecipeCreator;
import com.oipie.core.recipes.application.RecipeShower;
import com.oipie.core.recipes.domain.Recipe;
import com.oipie.core.recipes.domain.RecipeId;
import com.oipie.core.recipes.infrastructure.rest.dtos.CreateRecipeRequestDTO;
import com.oipie.core.recipes.infrastructure.rest.dtos.RecipeResponseDTO;
import com.oipie.core.shared.domain.DomainError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/recipes")
public class RecipesController {

    private final RecipeCreator recipeCreator;

    private final RecipeShower recipeShower;

    public RecipesController(RecipeCreator recipeCreator, RecipeShower recipeShower) {
        this.recipeCreator = recipeCreator;
        this.recipeShower = recipeShower;
    }


    @Transactional
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public RecipeResponseDTO createRecipe(@Valid @RequestBody CreateRecipeRequestDTO requestDTO) {

        Recipe recipe = this.recipeCreator.execute(requestDTO.name(), requestDTO.preparationTime(), requestDTO.cover());

        return RecipeResponseDTO.fromDomain(recipe);

    }

    @Transactional
    @GetMapping("/{recipeId}")
    @ResponseStatus(HttpStatus.OK)
    RecipeResponseDTO getRecipe(@PathVariable("recipeId") String recipeId) throws DomainError {
        Recipe recipe = this.recipeShower.execute(RecipeId.fromString(recipeId));

        return RecipeResponseDTO.fromDomain(recipe);
    }
}
