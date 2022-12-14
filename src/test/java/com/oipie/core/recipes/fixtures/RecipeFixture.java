package com.oipie.core.recipes.fixtures;

import com.oipie.core.recipes.domain.Recipe;
import com.oipie.core.recipes.domain.primitives.RecipePrimitives;

public class RecipeFixture {

    public static final RecipePrimitives PUMPKIN_PRIMITIVES = new RecipePrimitives(
            "8e8adfd7-33c0-4796-83c1-7b9916d8390c",
            "pumpkin_recipe",
            2,
            "fake_image"
    );

    public static Recipe pumpkin() {
        return Recipe.fromPrimitives(PUMPKIN_PRIMITIVES);
    }
}
