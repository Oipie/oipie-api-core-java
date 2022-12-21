package com.oipie.core.recipes.infrastructure.rest.dtos;

import com.oipie.core.recipes.domain.Recipe;

import java.util.List;

public record UserLikedRecipesResponseDTO(
        List<RecipeResponseDTO> likedRecipes) {

    public static UserLikedRecipesResponseDTO fromDomain(List<Recipe> userLikedRecipes) {
        return new UserLikedRecipesResponseDTO(userLikedRecipes.stream().map(RecipeResponseDTO::fromDomain).toList());
    }
}
