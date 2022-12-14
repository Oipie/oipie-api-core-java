package com.oipie.core.users.infrastructure.rest;

import com.oipie.core.BaseTestClient;
import com.oipie.core.shared.domain.DomainErrorCode;
import com.oipie.core.users.infrastructure.rest.dtos.CreateUserRequestDTO;
import com.oipie.core.users.infrastructure.rest.dtos.LoginUserRequestDTO;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.oipie.core.users.fixtures.UserFixture.LUIGI_PRIMITIVES;
import static org.hamcrest.Matchers.equalTo;


public class UserCreatorTest extends BaseTestClient {

    final String FAKE_EMAIL = LUIGI_PRIMITIVES.email();
    final String FAKE_NICKNAME = LUIGI_PRIMITIVES.nickname();
    final String FAKE_PASSWORD = LUIGI_PRIMITIVES.password();


    @Test
    public void creates_a_new_user() {

        CreateUserRequestDTO request = new CreateUserRequestDTO(FAKE_EMAIL, FAKE_NICKNAME, FAKE_PASSWORD);

        ValidatableResponse response = this.request()
                .body(request)
                .post("/v1/users")
                .then();

        response.statusCode(HttpStatus.CREATED.value());

        LoginUserRequestDTO loginRequest = new LoginUserRequestDTO(FAKE_EMAIL, FAKE_PASSWORD);

        ValidatableResponse loginResponse = this.request()
                .body(loginRequest)
                .post("/v1/users/login")
                .then();
        loginResponse.statusCode(HttpStatus.OK.value());

    }

    @Test
    public void fails_if_invalid_email() {

        CreateUserRequestDTO request = new CreateUserRequestDTO("a.com", FAKE_NICKNAME, FAKE_PASSWORD);

        ValidatableResponse response = this.request()
                .body(request)
                .post("/v1/users")
                .then();

        response.statusCode(HttpStatus.BAD_REQUEST.value());
        response.body("domainErrorCode", equalTo(DomainErrorCode.INVALID_EMAIL.name()));
    }


    @Test
    public void fails_if_password_is_too_short() {

        CreateUserRequestDTO request = new CreateUserRequestDTO(FAKE_EMAIL, FAKE_NICKNAME, "short");

        ValidatableResponse response = this.request()
                .body(request)
                .post("/v1/users")
                .then();

        response.statusCode(HttpStatus.BAD_REQUEST.value());
        response.body("domainErrorCode", equalTo(DomainErrorCode.INVALID_PASSWORD.name()));
    }

    @Test
    public void fails_if_email_is_registered() {

        CreateUserRequestDTO request = new CreateUserRequestDTO(FAKE_EMAIL, FAKE_NICKNAME, "fake_password_user_1");
        CreateUserRequestDTO secondRequest = new CreateUserRequestDTO(FAKE_EMAIL, "new_username", "fake_password_user_1");

        ValidatableResponse firstResponse = this.request()
                .body(request)
                .post("/v1/users")
                .then();

        firstResponse.statusCode(HttpStatus.CREATED.value());

        ValidatableResponse response = this.request()
                .body(secondRequest)
                .post("/v1/users")
                .then();

        response.statusCode(HttpStatus.BAD_REQUEST.value());
        response.body("domainErrorCode", equalTo(DomainErrorCode.EMAIL_ALREADY_IN_USE.name()));
    }

    @Test
    public void fails_if_nickname_is_registered() {

        CreateUserRequestDTO request = new CreateUserRequestDTO("a@b.com", FAKE_NICKNAME, "fake_password_user_1");
        CreateUserRequestDTO secondRequest = new CreateUserRequestDTO(FAKE_EMAIL, FAKE_NICKNAME, "fake_password_user_1");

        ValidatableResponse firstResponse = this.request()
                .body(request)
                .post("/v1/users")
                .then();

        firstResponse.statusCode(HttpStatus.CREATED.value());

        ValidatableResponse response = this.request()
                .body(secondRequest)
                .post("/v1/users")
                .then();

        response.statusCode(HttpStatus.BAD_REQUEST.value());
        response.body("domainErrorCode", equalTo(DomainErrorCode.NICKNAME_ALREADY_IN_USE.name()));
    }
}
