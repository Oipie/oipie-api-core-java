package com.oipie.core.recipes.infrastructure.rest;

import com.oipie.core.recipes.application.RecipeCreator;
import com.oipie.core.recipes.application.RecipeLister;
import com.oipie.core.recipes.application.RecipeShower;
import com.oipie.core.recipes.domain.Recipe;
import com.oipie.core.recipes.domain.RecipeId;
import com.oipie.core.recipes.infrastructure.rest.dtos.CreateRecipeRequestDTO;
import com.oipie.core.recipes.infrastructure.rest.dtos.RecipeResponseDTO;
import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.shared.domain.PageResult;
import com.oipie.core.shared.infrastructure.rest.dtos.PageResultDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/recipes")
public class RecipesController {

    private final RecipeCreator recipeCreator;

    private final RecipeShower recipeShower;

    private final RecipeLister recipeLister;

    public RecipesController(RecipeCreator recipeCreator, RecipeShower recipeShower, RecipeLister recipeLister) {
        this.recipeCreator = recipeCreator;
        this.recipeShower = recipeShower;
        this.recipeLister = recipeLister;
    }


    @Transactional
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public RecipeResponseDTO createRecipe(@Valid @RequestBody CreateRecipeRequestDTO requestDTO) throws DomainError {

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

    @Transactional
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    PageResultDTO<RecipeResponseDTO> listRecipes(@RequestParam("page") int page, @RequestParam("limit") int limit) throws DomainError {
        PageResult<Recipe> recipes = this.recipeLister.execute(page, limit);

        return new PageResultDTO<RecipeResponseDTO>(
                recipes.getResults().stream().map(RecipeResponseDTO::fromDomain).toList(),
                page,
                limit,
                recipes.getTotalResults());
    }
}
