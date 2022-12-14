package com.oipie.core.recipes.infrastructure.rest.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public record CreateRecipeRequestDTO(
        @NotBlank
        String name,

        @Min(0)
        int preparationTime,

        String cover) {
}
