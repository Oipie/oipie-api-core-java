package com.oipie.core.recipes.infrastructure.persistence;

import com.oipie.core.BaseTestClient;
import com.oipie.core.recipes.domain.Recipe;
import com.oipie.core.recipes.domain.RecipeId;
import com.oipie.core.recipes.domain.RecipeRepository;
import com.oipie.core.recipes.domain.primitives.RecipePrimitives;
import com.oipie.core.recipes.fixtures.RecipeFixture;
import com.oipie.core.shared.domain.PageResult;
import com.oipie.core.users.domain.User;
import com.oipie.core.users.domain.UserId;
import com.oipie.core.users.domain.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static com.oipie.core.recipes.fixtures.RecipeFixture.PAELLA_PRIMITIVES;
import static com.oipie.core.recipes.fixtures.RecipeFixture.PUMPKIN_PRIMITIVES;

public class RecipeRepositoryPostgresTest extends BaseTestClient {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void beforeEach() {
        PUMPKIN_PRIMITIVES.likes().users().forEach((user) -> this.userRepository.save(User.fromPrimitives(user)));
        PAELLA_PRIMITIVES.likes().users().forEach((user) -> this.userRepository.save(User.fromPrimitives(user)));
    }

    @Test
    public void it_should_save_a_recipe() {
        Recipe pumpkin = RecipeFixture.pumpkin();

        this.recipeRepository.save(pumpkin);

        Optional<Recipe> result = this.recipeRepository.findByRecipeId(RecipeId.fromString(PUMPKIN_PRIMITIVES.id()));
        Assertions.assertEquals(pumpkin.toPrimitives(), result.get().toPrimitives(), "Retrieved recipe is not equal");
    }

    @Test
    public void finds_saved_recipes() {
        this.recipeRepository.save(RecipeFixture.pumpkin());
        this.recipeRepository.save(RecipeFixture.paella());

        PageResult<Recipe> result = this.recipeRepository.find(0, 2);

        List<RecipePrimitives> foundRecipes = result.getResults().stream().map(Recipe::toPrimitives).toList();
        Assertions.assertEquals(List.of(PUMPKIN_PRIMITIVES, PAELLA_PRIMITIVES), foundRecipes, "Found recipes are not equal");
    }

    @Test
    public void returns_user_liked_recipes() {
        this.recipeRepository.save(RecipeFixture.pumpkin());
        String userId = PUMPKIN_PRIMITIVES.likes().users().stream().toList().get(0).userId();

        List<Recipe> result = this.recipeRepository.getUserLikedRecipes(UserId.fromString(userId));

        Assertions.assertEquals(List.of(PUMPKIN_PRIMITIVES), result.stream().map(Recipe::toPrimitives).toList(), "User liked recipes are not equal");
    }
}
