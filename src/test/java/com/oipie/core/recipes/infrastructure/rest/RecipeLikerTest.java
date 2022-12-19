package com.oipie.core.recipes.infrastructure.rest;

import com.oipie.core.BaseTestClient;
import com.oipie.core.recipes.infrastructure.rest.dtos.CreateRecipeRequestDTO;
import com.oipie.core.users.infrastructure.rest.dtos.CreateUserRequestDTO;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.oipie.core.recipes.fixtures.RecipeFixture.PUMPKIN_PRIMITIVES;
import static com.oipie.core.users.fixtures.UserFixture.LUIGI_PRIMITIVES;
import static org.hamcrest.Matchers.equalTo;

public class RecipeLikerTest extends BaseTestClient {

    final String FAKE_EMAIL = LUIGI_PRIMITIVES.email();

    final String FAKE_NICKNAME = LUIGI_PRIMITIVES.nickname();

    final String FAKE_PASSWORD = LUIGI_PRIMITIVES.password();

    final String PUMPKIN_NAME = PUMPKIN_PRIMITIVES.name();

    final int PUMPKIN_PREPARATION_TIME = PUMPKIN_PRIMITIVES.preparationTime();

    final String PUMPKIN_COVER = PUMPKIN_PRIMITIVES.cover();

    private String createdRecipeId;

    @BeforeEach
    public void beforeEach() {
        CreateUserRequestDTO userRequest = new CreateUserRequestDTO(FAKE_EMAIL, FAKE_NICKNAME, FAKE_PASSWORD);

        ValidatableResponse userResponse = this.request()
                .body(userRequest)
                .post("/v1/users")
                .then();

        userResponse.statusCode(HttpStatus.CREATED.value());

        CreateRecipeRequestDTO recipeRequest = new CreateRecipeRequestDTO(PUMPKIN_NAME, PUMPKIN_PREPARATION_TIME, PUMPKIN_COVER);

        ValidatableResponse recipeResponse = this.request()
                .body(recipeRequest)
                .post("/v1/recipes")
                .then();

        recipeResponse.statusCode(HttpStatus.CREATED.value());

        createdRecipeId = recipeResponse.extract().body().path("id");
    }

    @Test
    public void adds_user_like_to_recipe() {
        ValidatableResponse likeResponse = this.request()
                .post("/v1/recipes/" + createdRecipeId + "/like")
                .then();

        likeResponse.statusCode(HttpStatus.OK.value());

        ValidatableResponse recipeResponse = this.request()
                .get("/v1/recipes/" + createdRecipeId)
                .then();

        recipeResponse.statusCode(HttpStatus.OK.value());
        recipeResponse.body("likesAmount", equalTo(1));
    }
}
