package com.oipie.core.recipes.domain;

import com.oipie.core.recipes.domain.primitives.RecipePrimitives;
import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.shared.domain.ddd.Aggregate;
import com.oipie.core.users.domain.User;

public final class Recipe extends Aggregate {

    private final RecipeId recipeId;

    private final String name;

    private final int preparationTime;

    private final String cover;

    private final Likes likes;

    private Recipe(RecipeId recipeId, String name, int preparationTime, String cover, Likes likes) {
        this.recipeId = recipeId;
        this.name = name;
        this.preparationTime = preparationTime;
        this.cover = cover;
        this.likes = likes;
    }

    public static Recipe create(RecipeId recipeId, String name, int preparationTime, String cover) {
        return new Recipe(recipeId, name, preparationTime, cover, Likes.create());
    }

    public static Recipe fromPrimitives(RecipePrimitives recipePrimitives) throws DomainError {
        return new Recipe(
                RecipeId.fromString(recipePrimitives.id()),
                recipePrimitives.name(),
                recipePrimitives.preparationTime(),
                recipePrimitives.cover(),
                Likes.fromPrimitives(recipePrimitives.likes())
        );
    }

    public void addLike(User user) {
        this.likes.addLike(user);
    }

    public long getLikesAmount() {
        return this.likes.getLikesAmount();
    }

    public RecipePrimitives toPrimitives() {
        return new RecipePrimitives(
                this.recipeId.toString(),
                this.name,
                this.preparationTime,
                this.cover,
                this.likes.toPrimitives()
        );
    }
}
