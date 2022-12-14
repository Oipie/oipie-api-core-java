package com.oipie.core.recipes.infrastructure.persistence;

import com.oipie.core.recipes.domain.Recipe;
import com.oipie.core.recipes.domain.RecipeId;
import com.oipie.core.recipes.domain.RecipeRepository;
import com.oipie.core.recipes.infrastructure.persistence.entities.RecipeEntity;

import java.util.Optional;

public class RecipeRepositoryPostgres implements RecipeRepository {

    private final RecipeRepositoryJPA repository;

    public RecipeRepositoryPostgres(RecipeRepositoryJPA repository) {
        this.repository = repository;
    }

    @Override
    public void save(Recipe recipe) {
        this.repository.save(RecipeEntity.fromDomain(recipe));
    }

    @Override
    public Optional<Recipe> findByRecipeId(RecipeId recipeId) {
        Optional<RecipeEntity> recipeEntity = this.repository.findById(recipeId.toString());
        if (recipeEntity.isEmpty()) return Optional.empty();
        
        return Optional.of(recipeEntity.get().toDomain());
    }
}
