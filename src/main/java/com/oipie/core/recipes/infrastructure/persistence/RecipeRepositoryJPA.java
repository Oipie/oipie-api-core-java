package com.oipie.core.recipes.infrastructure.persistence;

import com.oipie.core.recipes.infrastructure.persistence.entities.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepositoryJPA extends JpaRepository<RecipeEntity, String> {
}
