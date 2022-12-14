package com.oipie.core.users.infrastructure.rest;

import com.oipie.core.BaseTestClient;
import com.oipie.core.shared.domain.DomainErrorCode;
import com.oipie.core.users.infrastructure.rest.dtos.CreateUserRequestDTO;
import com.oipie.core.users.infrastructure.rest.dtos.LoginUserRequestDTO;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.oipie.core.users.fixtures.UserFixture.LUIGI_PRIMITIVES;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class UserAuthorizerTest extends BaseTestClient {

    final String FAKE_EMAIL = LUIGI_PRIMITIVES.email();
    final String FAKE_NICKNAME = LUIGI_PRIMITIVES.nickname();
    final String FAKE_PASSWORD = LUIGI_PRIMITIVES.password();


    @BeforeEach
    public void beforeEach() {
        CreateUserRequestDTO request = new CreateUserRequestDTO(FAKE_EMAIL, FAKE_NICKNAME, FAKE_PASSWORD);
        ValidatableResponse response = this.request()
                .body(request)
                .post("/v1/users")
                .then();

        response.statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void logins_a_new_user() {
        LoginUserRequestDTO loginRequest = new LoginUserRequestDTO(FAKE_EMAIL, FAKE_PASSWORD);

        ValidatableResponse loginResponse = this.request()
                .body(loginRequest)
                .post("/v1/users/login")
                .then();
        loginResponse.statusCode(HttpStatus.OK.value());
        loginResponse.body("jwt", notNullValue());
    }

    @Test
    public void fails_if_password_is_not_correct() {
        LoginUserRequestDTO loginRequest = new LoginUserRequestDTO(FAKE_EMAIL, "NON_VALID_PASSWORD");

        ValidatableResponse loginResponse = this.request()
                .body(loginRequest)
                .post("/v1/users/login")
                .then();
        loginResponse.statusCode(HttpStatus.UNAUTHORIZED.value());
        loginResponse.body("domainErrorCode", equalTo(DomainErrorCode.LOGIN_FAILED.name()));
    }

    @Test
    public void fails_if_email_is_not_correct() {
        LoginUserRequestDTO loginRequest = new LoginUserRequestDTO("non@valid.com", FAKE_PASSWORD);

        ValidatableResponse loginResponse = this.request()
                .body(loginRequest)
                .post("/v1/users/login")
                .then();
        loginResponse.statusCode(HttpStatus.UNAUTHORIZED.value());
        loginResponse.body("domainErrorCode", equalTo(DomainErrorCode.LOGIN_FAILED.name()));
    }
}
