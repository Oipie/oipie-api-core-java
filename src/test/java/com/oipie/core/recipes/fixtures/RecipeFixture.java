package com.oipie.core.recipes.fixtures;

import com.oipie.core.recipes.domain.Recipe;
import com.oipie.core.recipes.domain.primitives.LikesPrimitives;
import com.oipie.core.recipes.domain.primitives.RecipePrimitives;
import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.users.fixtures.UserFixture;

import java.util.Set;

public class RecipeFixture {

    public static final RecipePrimitives PUMPKIN_PRIMITIVES = new RecipePrimitives(
            "8e8adfd7-33c0-4796-83c1-7b9916d8390c",
            "pumpkin",
            2,
            "fake_image",
            new LikesPrimitives(Set.of(UserFixture.LUIGI_PRIMITIVES))
    );

    public static final RecipePrimitives PAELLA_PRIMITIVES = new RecipePrimitives(
            "0d014f2e-2bbc-450c-abc7-051e61df745e",
            "paella",
            3,
            "fake_image",
            new LikesPrimitives(Set.of(UserFixture.LUIGI_PRIMITIVES))
    );


    public static Recipe pumpkin() throws DomainError {
        return Recipe.fromPrimitives(PUMPKIN_PRIMITIVES);
    }

    public static Recipe paella() throws DomainError {
        return Recipe.fromPrimitives(PAELLA_PRIMITIVES);
    }
}
