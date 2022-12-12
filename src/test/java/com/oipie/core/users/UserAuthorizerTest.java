package com.oipie.core.users;

import com.oipie.core.TestInjectionConfiguration;
import com.oipie.core.shared.domain.DomainErrorCode;
import com.oipie.core.users.infrastructure.rest.dtos.CreateUserRequestDTO;
import com.oipie.core.users.infrastructure.rest.dtos.LoginUserRequestDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.transaction.Transactional;

import static com.oipie.core.users.fixtures.UserFixture.LUIGI_PRIMITIVES;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestInjectionConfiguration.class)
@Transactional
public class UserAuthorizerTest {

    @LocalServerPort
    int port;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void execute(){
        jdbcTemplate.execute("TRUNCATE TABLE users");
        CreateUserRequestDTO request = new CreateUserRequestDTO(FAKE_EMAIL,FAKE_NICKNAME,FAKE_PASSWORD);

        ValidatableResponse response = RestAssured.given()
                .basePath("/api")
                .port(this.port)
                .contentType(ContentType.JSON)
                .body(request)
                .post("/v1/users")
                .then();

        response.statusCode(HttpStatus.CREATED.value());
    }

    final String FAKE_EMAIL = LUIGI_PRIMITIVES.email();
    final String FAKE_NICKNAME = LUIGI_PRIMITIVES.nickname();
    final String FAKE_PASSWORD = LUIGI_PRIMITIVES.password();


    @Test
    public void logins_a_new_user() {
        LoginUserRequestDTO loginRequest = new LoginUserRequestDTO(FAKE_EMAIL,FAKE_PASSWORD);

        ValidatableResponse loginResponse = RestAssured.given()
                .basePath("/api")
                .port(this.port)
                .contentType(ContentType.JSON)
                .body(loginRequest)
                .post("/v1/users/login")
                .then();
        loginResponse.statusCode(HttpStatus.OK.value());
        loginResponse.body("jwt",notNullValue());
    }

    @Test
    public void fails_if_password_is_not_correct() {
        LoginUserRequestDTO loginRequest = new LoginUserRequestDTO(FAKE_EMAIL,"NON_VALID_PASSWORD");

        ValidatableResponse loginResponse = RestAssured.given()
                .basePath("/api")
                .port(this.port)
                .contentType(ContentType.JSON)
                .body(loginRequest)
                .post("/v1/users/login")
                .then();
        loginResponse.statusCode(HttpStatus.UNAUTHORIZED.value());
        loginResponse.body("domainErrorCode",equalTo(DomainErrorCode.LOGIN_FAILED.name()));
    }

    @Test
    public void fails_if_email_is_not_correct() {
        LoginUserRequestDTO loginRequest = new LoginUserRequestDTO("non@valid.com",FAKE_PASSWORD);

        ValidatableResponse loginResponse = RestAssured.given()
                .basePath("/api")
                .port(this.port)
                .contentType(ContentType.JSON)
                .body(loginRequest)
                .post("/v1/users/login")
                .then();
        loginResponse.statusCode(HttpStatus.UNAUTHORIZED.value());
        loginResponse.body("domainErrorCode",equalTo(DomainErrorCode.LOGIN_FAILED.name()));
    }
}
