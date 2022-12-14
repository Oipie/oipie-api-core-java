package com.oipie.core.recipes.infrastructure.rest;

import com.oipie.core.BaseTestClient;
import com.oipie.core.recipes.infrastructure.rest.dtos.CreateRecipeRequestDTO;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.oipie.core.recipes.fixtures.RecipeFixture.PUMPKIN_PRIMITIVES;
import static org.hamcrest.Matchers.equalTo;


public class RecipesCreatorTest extends BaseTestClient {

    final String FAKE_NAME = PUMPKIN_PRIMITIVES.name();

    final int FAKE_PREPARATION_TIME = PUMPKIN_PRIMITIVES.preparationTime();

    final String FAKE_COVER = PUMPKIN_PRIMITIVES.cover();

    @Test
    public void creates_a_new_recipes() {

        CreateRecipeRequestDTO request = new CreateRecipeRequestDTO(FAKE_NAME, FAKE_PREPARATION_TIME, FAKE_COVER);

        ValidatableResponse firstResponse = this.request()
                .body(request)
                .post("/v1/recipes")
                .then();

        firstResponse.statusCode(HttpStatus.CREATED.value());

        String recipeId = firstResponse.extract().body().path("id");

        ValidatableResponse response = this.request()
                .get("/v1/recipes/" + recipeId)
                .then();

        response.statusCode(HttpStatus.OK.value());
        response.body("name", equalTo(FAKE_NAME));
        response.body("preparationTime", equalTo(FAKE_PREPARATION_TIME));
        response.body("cover", equalTo(FAKE_COVER));
    }
}
