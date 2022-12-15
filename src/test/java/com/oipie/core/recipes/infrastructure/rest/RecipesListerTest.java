package com.oipie.core.recipes.infrastructure.rest;

import com.oipie.core.BaseTestClient;
import com.oipie.core.recipes.infrastructure.rest.dtos.CreateRecipeRequestDTO;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.oipie.core.recipes.fixtures.RecipeFixture.PAELLA_PRIMITIVES;
import static com.oipie.core.recipes.fixtures.RecipeFixture.PUMPKIN_PRIMITIVES;
import static org.hamcrest.Matchers.equalTo;


public class RecipesListerTest extends BaseTestClient {

    final String PUMPKIN_NAME = PUMPKIN_PRIMITIVES.name();

    final int PUMPKIN_PREPARATION_TIME = PUMPKIN_PRIMITIVES.preparationTime();

    final String PUMPKIN_COVER = PUMPKIN_PRIMITIVES.cover();

    final String PAELLA_NAME = PAELLA_PRIMITIVES.name();

    final int PAELLA_PREPARATION_TIME = PAELLA_PRIMITIVES.preparationTime();

    final String PAELLA_COVER = PAELLA_PRIMITIVES.cover();


    @BeforeEach
    public void beforeEach() {
        CreateRecipeRequestDTO firstRequest = new CreateRecipeRequestDTO(PUMPKIN_NAME, PUMPKIN_PREPARATION_TIME, PUMPKIN_COVER);
        CreateRecipeRequestDTO secondRequest = new CreateRecipeRequestDTO(PAELLA_NAME, PAELLA_PREPARATION_TIME, PAELLA_COVER);

        ValidatableResponse firstResponse = this.request()
                .body(firstRequest)
                .post("/v1/recipes")
                .then();

        firstResponse.statusCode(HttpStatus.CREATED.value());

        ValidatableResponse secondResponse = this.request()
                .body(secondRequest)
                .post("/v1/recipes")
                .then();

        secondResponse.statusCode(HttpStatus.CREATED.value());

    }

    @Test
    public void lists_a_recipe() {
        int PAGE = 0;
        int LIMIT = 1;
        int TOTAL_RESULTS = 2;

        ValidatableResponse response = this.request()
                .queryParam("page", PAGE)
                .queryParam("limit", LIMIT)
                .get("/v1/recipes")
                .then();

        response.statusCode(HttpStatus.OK.value());
        response.body("result.get(0).name", equalTo(PUMPKIN_NAME));
        response.body("result.get(0).preparationTime", equalTo(PUMPKIN_PREPARATION_TIME));
        response.body("result.get(0).cover", equalTo(PUMPKIN_COVER));
        response.body("page", equalTo(PAGE));
        response.body("limit", equalTo(LIMIT));
        response.body("totalResults", equalTo(TOTAL_RESULTS));
    }

    @Test
    public void returns_empty_list_if_no_more_results() {
        int PAGE = 3;
        int LIMIT = 4;
        int TOTAL_RESULTS = 2;

        ValidatableResponse response = this.request()
                .queryParam("page", PAGE)
                .queryParam("limit", LIMIT)
                .get("/v1/recipes")
                .then();

        response.statusCode(HttpStatus.OK.value());
        response.body("result.size()", equalTo(0));
        response.body("page", equalTo(PAGE));
        response.body("limit", equalTo(LIMIT));
        response.body("totalResults", equalTo(TOTAL_RESULTS));
    }

}