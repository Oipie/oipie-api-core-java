package com.oipie.core.recipes.infrastructure.persistence.entities;

import com.oipie.core.recipes.domain.Recipe;
import com.oipie.core.recipes.domain.primitives.LikesPrimitives;
import com.oipie.core.recipes.domain.primitives.RecipePrimitives;
import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.users.infrastructure.persistence.entities.UserEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;
import java.util.stream.Collectors;

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

    @ManyToMany
    @JoinTable(
            name = "user_likes",
            joinColumns = {@JoinColumn(name = "recipe_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<UserEntity> likesByUsers;

    public static RecipeEntity fromDomain(Recipe recipe) {
        RecipePrimitives recipePrimitives = recipe.toPrimitives();

        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.recipeId = recipePrimitives.id();
        recipeEntity.name = recipePrimitives.name();
        recipeEntity.preparationTime = recipePrimitives.preparationTime();
        recipeEntity.cover = recipePrimitives.cover();
        recipeEntity.likesByUsers = recipePrimitives.likes().users().stream().map(UserEntity::fromPrimitives).collect(Collectors.toSet());

        return recipeEntity;
    }

    public Recipe toDomain() throws DomainError {
        RecipePrimitives recipePrimitives = new RecipePrimitives(
                this.recipeId,
                this.name,
                this.preparationTime,
                this.cover,
                new LikesPrimitives(this.likesByUsers.stream().map(UserEntity::toPrimitives).collect(Collectors.toSet())));

        return Recipe.fromPrimitives(recipePrimitives);
    }

}
