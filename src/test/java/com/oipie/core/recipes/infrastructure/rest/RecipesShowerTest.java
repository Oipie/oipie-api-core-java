package com.oipie.core.recipes.infrastructure.rest;

import com.oipie.core.BaseTestClient;
import com.oipie.core.recipes.infrastructure.rest.dtos.CreateRecipeRequestDTO;
import com.oipie.core.shared.domain.DomainErrorCode;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.oipie.core.recipes.fixtures.RecipeFixture.PUMPKIN_PRIMITIVES;
import static org.hamcrest.Matchers.equalTo;


public class RecipesShowerTest extends BaseTestClient {

    final String FAKE_NAME = PUMPKIN_PRIMITIVES.name();

    final int FAKE_PREPARATION_TIME = PUMPKIN_PRIMITIVES.preparationTime();

    final String FAKE_COVER = PUMPKIN_PRIMITIVES.cover();

    private String createdRecipeId;

    @BeforeEach
    public void beforeEach() {
        CreateRecipeRequestDTO request = new CreateRecipeRequestDTO(FAKE_NAME, FAKE_PREPARATION_TIME, FAKE_COVER);

        ValidatableResponse response = this.request()
                .body(request)
                .post("/v1/recipes")
                .then();

        response.statusCode(HttpStatus.CREATED.value());

        createdRecipeId = response.extract().body().path("id");
    }

    @Test
    public void gets_a_recipe() {
        ValidatableResponse response = this.request()
                .get("/v1/recipes/" + createdRecipeId)
                .then();

        response.statusCode(HttpStatus.OK.value());
        response.body("name", equalTo(FAKE_NAME));
        response.body("preparationTime", equalTo(FAKE_PREPARATION_TIME));
        response.body("cover", equalTo(FAKE_COVER));
    }

    @Test
    public void fails_if_recipe_not_found() {
        String FAKE_ID = "0d014f2e-2bbc-450c-abc7-051e61df745e";
        ValidatableResponse response = this.request()
                .get("/v1/recipes/" + FAKE_ID)
                .then();

        response.statusCode(HttpStatus.NOT_FOUND.value());
        response.body("domainErrorCode", equalTo(DomainErrorCode.RECIPE_NOT_FOUND.name()));
    }

    @Test
    public void fails_if_recipe_id_not_correct() {
        ValidatableResponse response = this.request()
                .get("/v1/recipes/" + "FAKE_ID")
                .then();

        response.statusCode(HttpStatus.BAD_REQUEST.value());
        response.body("domainErrorCode", equalTo(DomainErrorCode.INVALID_ID.name()));
    }
}
