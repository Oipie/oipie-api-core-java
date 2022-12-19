package com.oipie.core.users.infrastructure.persistence;

import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.users.domain.Email;
import com.oipie.core.users.domain.User;
import com.oipie.core.users.domain.UserId;
import com.oipie.core.users.domain.UserRepository;
import com.oipie.core.users.infrastructure.persistence.entities.UserEntity;

import java.util.Optional;

public class UserRepositoryPostgres implements UserRepository {

    private final UserRepositoryJPA repository;

    public UserRepositoryPostgres(UserRepositoryJPA repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> findById(UserId userId) {
        Optional<UserEntity> userEntity = this.repository.findById(userId.toString());
        if (userEntity.isEmpty()) return Optional.empty();
        return Optional.of(userEntity.get().toDomain());
    }


    @Override
    public Optional<User> findByEmail(Email email) throws DomainError {
        Optional<UserEntity> userEntity = this.repository.findOneByEmail(email.toPrimitives());
        if (userEntity.isEmpty()) return Optional.empty();
        return Optional.of(userEntity.get().toDomain());
    }

    @Override
    public boolean isNicknameInUse(String nickname) {
        return this.repository.findOneByNickname(nickname).isPresent();
    }

    @Override
    public void save(User user) {
        this.repository.save(UserEntity.fromDomain(user));
    }
}
