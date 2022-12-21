package com.oipie.core.users.infrastructure.persistence;


import com.oipie.core.BaseTestClient;
import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.users.domain.Email;
import com.oipie.core.users.domain.User;
import com.oipie.core.users.domain.UserRepository;
import com.oipie.core.users.fixtures.UserFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserRepositoryPostgresTest extends BaseTestClient {

    @Autowired
    UserRepository userRepository;

    @Test
    public void it_should_save_user() throws DomainError {
        User luigi = UserFixture.luigi();
        this.userRepository.save(luigi);
        Optional<User> result = this.userRepository.findByEmail(Email.fromString(UserFixture.LUIGI_PRIMITIVES.email()));
        Assertions.assertEquals(result.get(), luigi, "Retrieved user is not equal ");
    }

    @Test
    public void returns_empty_optional_if_not_found() throws DomainError {
        Optional<User> result = this.userRepository.findByEmail(Email.fromString("Fake@mail.com"));
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void returns_true_if_nickname_is_used() throws DomainError {
        User luigi = UserFixture.luigi();
        this.userRepository.save(luigi);
        boolean result = this.userRepository.isNicknameInUse(UserFixture.LUIGI_PRIMITIVES.nickname());
        Assertions.assertTrue(result);
    }

    @Test
    public void returns_false_if_nickname_is_not_used() {
        boolean result = this.userRepository.isNicknameInUse("FAKE_NICKNAME_NON_IN_USE");
        Assertions.assertFalse(result);
    }

}
