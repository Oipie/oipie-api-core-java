package com.oipie.core.recipes.infrastructure.persistence.entities;

import com.oipie.core.recipes.domain.Recipe;
import com.oipie.core.recipes.domain.primitives.RecipePrimitives;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "recipes")
public class RecipeEntity {

    @Id
    @Column(name = "recipe_id", nullable = false)
    private String recipeId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "preparation_time", nullable = false)
    private int preparationTime;

    @Column(name = "cover", nullable = false)
    private String cover;

    public static RecipeEntity fromDomain(Recipe recipe) {
        RecipePrimitives recipePrimitives = recipe.toPrimitives();

        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.recipeId = recipePrimitives.id();
        recipeEntity.name = recipePrimitives.name();
        recipeEntity.preparationTime = recipePrimitives.preparationTime();
        recipeEntity.cover = recipePrimitives.cover();

        return recipeEntity;
    }

    public Recipe toDomain() {
        RecipePrimitives recipePrimitives = new RecipePrimitives(this.recipeId, this.name, this.preparationTime, this.cover);

        return Recipe.fromPrimitives(recipePrimitives);
    }

}
