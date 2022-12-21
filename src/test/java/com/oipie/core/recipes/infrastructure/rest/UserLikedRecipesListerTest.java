package com.oipie.core.recipes.infrastructure.rest;

import com.oipie.core.BaseTestClient;
import com.oipie.core.recipes.infrastructure.rest.dtos.CreateRecipeRequestDTO;
import com.oipie.core.users.infrastructure.rest.dtos.CreateUserRequestDTO;
import com.oipie.core.users.infrastructure.rest.dtos.LoginUserRequestDTO;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.oipie.core.recipes.fixtures.RecipeFixture.PAELLA_PRIMITIVES;
import static com.oipie.core.recipes.fixtures.RecipeFixture.PUMPKIN_PRIMITIVES;
import static com.oipie.core.users.fixtures.UserFixture.LUIGI_PRIMITIVES;
import static org.hamcrest.Matchers.equalTo;


public class UserLikedRecipesListerTest extends BaseTestClient {

    final String FAKE_EMAIL = LUIGI_PRIMITIVES.email();

    final String FAKE_NICKNAME = LUIGI_PRIMITIVES.nickname();

    final String FAKE_PASSWORD = LUIGI_PRIMITIVES.password();

    final String PUMPKIN_NAME = PUMPKIN_PRIMITIVES.name();

    final int PUMPKIN_PREPARATION_TIME = PUMPKIN_PRIMITIVES.preparationTime();

    final String PUMPKIN_COVER = PUMPKIN_PRIMITIVES.cover();

    final String PAELLA_NAME = PAELLA_PRIMITIVES.name();

    final int PAELLA_PREPARATION_TIME = PAELLA_PRIMITIVES.preparationTime();

    final String PAELLA_COVER = PAELLA_PRIMITIVES.cover();

    private String pumpkinCreatedId;

    private String paellaCreatedId;

    private String userJwt;

    @BeforeEach
    public void beforeEach() {
        CreateUserRequestDTO userRequest = new CreateUserRequestDTO(FAKE_EMAIL, FAKE_NICKNAME, FAKE_PASSWORD);

        this.request().body(userRequest).post("/v1/users").then();

        LoginUserRequestDTO loginRequest = new LoginUserRequestDTO(FAKE_EMAIL, FAKE_PASSWORD);

        ValidatableResponse loginResponse = this.request()
                .body(loginRequest)
                .post("/v1/users/login")
                .then();

        loginResponse.statusCode(HttpStatus.OK.value());

        userJwt = loginResponse.extract().body().path("jwt");

        CreateRecipeRequestDTO pumpkinCreationRequest = new CreateRecipeRequestDTO(PUMPKIN_NAME, PUMPKIN_PREPARATION_TIME, PUMPKIN_COVER);

        ValidatableResponse pumpkinCreationResponse = this.request()
                .body(pumpkinCreationRequest)
                .post("/v1/recipes")
                .then();

        pumpkinCreationResponse.statusCode(HttpStatus.CREATED.value());

        CreateRecipeRequestDTO paellaCreationRequest = new CreateRecipeRequestDTO(PAELLA_NAME, PAELLA_PREPARATION_TIME, PAELLA_COVER);

        ValidatableResponse paellaCreationResponse = this.request()
                .body(paellaCreationRequest)
                .post("/v1/recipes")
                .then();

        paellaCreationResponse.statusCode(HttpStatus.CREATED.value());

        pumpkinCreatedId = pumpkinCreationResponse.extract().body().path("id");
        paellaCreatedId = paellaCreationResponse.extract().body().path("id");
    }

    @Test
    public void returns_an_empty_list_if_user_not_like_any_recipe() {
        ValidatableResponse response = this.request(userJwt)
                .get("/v1/recipes/user-liked-recipes")
                .then();

        response.statusCode(HttpStatus.OK.value());
        response.body("likedRecipes.size()", equalTo(0));
    }

    @Test
    public void returns_user_liked_recipes() {
        this.request(userJwt).post("/v1/recipes/" + pumpkinCreatedId + "/like").then();

        this.request(userJwt).post("/v1/recipes/" + paellaCreatedId + "/like").then();

        ValidatableResponse response = this.request(userJwt)
                .get("/v1/recipes/user-liked-recipes")
                .then();

        response.statusCode(HttpStatus.OK.value());
        response.body("likedRecipes.size()", equalTo(2));
        response.body("likedRecipes.get(0).name", equalTo(PUMPKIN_NAME));
        response.body("likedRecipes.get(1).name", equalTo(PAELLA_NAME));
    }
}
