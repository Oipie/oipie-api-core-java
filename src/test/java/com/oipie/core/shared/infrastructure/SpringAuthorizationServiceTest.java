package com.oipie.core.shared.infrastructure;

import com.oipie.core.shared.domain.AuthorizationService;
import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.shared.domain.Password;
import com.oipie.core.shared.infrastructure.auth.Roles;
import com.oipie.core.users.fixtures.UserFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class SpringAuthorizationServiceTest {


    private static final String FAKE_RAW_PASSWORD = "SUPER_SECURE_PASSWORD";
    private final Roles[] roles = {Roles.USER};
    @Autowired
    private AuthorizationService authorizationService;

    @Test
    public void should_verify_a_hashed_password() {
        Password result = this.authorizationService.hashPassword(FAKE_RAW_PASSWORD);
        Assertions.assertTrue(this.authorizationService.verifyPassword(result, FAKE_RAW_PASSWORD));

    }

    @Test
    public void encodes_a_JWT() throws DomainError {
        String result = this.authorizationService.createUserJWT(UserFixture.luigi().getUserId());
        Assertions.assertTrue(this.authorizationService.verifyJWT(result, this.roles));

    }

    @Test
    public void returns_false_if_not_valid_jwt() {
        Assertions.assertFalse(this.authorizationService.verifyJWT("FAKE_JWT", this.roles));
    }
}
