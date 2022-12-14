package com.oipie.core.users.domain;

import com.oipie.core.shared.domain.DomainError;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(Email email) throws DomainError;

    boolean isNicknameInUse(String nickname);

    void save(User user);
}
