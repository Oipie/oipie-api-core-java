package com.oipie.core.recipes.infrastructure.persistence;

import com.oipie.core.recipes.domain.Recipe;
import com.oipie.core.recipes.domain.RecipeRepository;
import com.oipie.core.recipes.infrastructure.persistence.entities.RecipeEntity;

public class RecipeRepositoryPostgres implements RecipeRepository {

    private final RecipeRepositoryJPA repository;

    public RecipeRepositoryPostgres(RecipeRepositoryJPA repository) {
        this.repository = repository;
    }

    @Override
    public void save(Recipe recipe) {
        this.repository.save(RecipeEntity.fromDomain(recipe));
    }
}
