package com.oipie.core;


import com.oipie.core.recipes.infrastructure.persistence.entities.RecipeEntity;
import com.oipie.core.users.infrastructure.persistence.entities.UserEntity;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.transaction.BeforeTransaction;

import javax.transaction.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestInjectionConfiguration.class)
@Transactional
public abstract class BaseTestClient {

    static final String USER_JWT = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwMDAwMDAwMC0wMDAwLTAwMDAtMDAwMC0wMDAwMDAwMDAwMDAiLCJyb2xlIjoiVVNFUiIsImlzcyI6Ik9pcGllIiwiZXhwIjoxNjcyMDU4MTczfQ.f5tVq7f_4wQyVg0jcxW6Z-86qrQFfjn-3lKdke1byCeHQjhAzn-lPzzTwBqy8tByZUEwXmiJl_HqwJKjW09i5w";

    @LocalServerPort
    private int port;

    @Autowired
    private JpaRepository<UserEntity, String> userRepository;


    @Autowired
    private JpaRepository<RecipeEntity, String> recipeRepository;


    @BeforeEach
    @BeforeTransaction
    public final void clearDatabase() {
        recipeRepository.deleteAll();
        userRepository.deleteAll();
    }

    public final RequestSpecification request(String jwt) {
        return RestAssured.given()
                .basePath("/api")
                .port(this.port)
                .contentType(ContentType.JSON)
                .header(new Header("Authorization", jwt));
    }


    public final RequestSpecification request() {
        return this.request(this.USER_JWT);
    }
}
