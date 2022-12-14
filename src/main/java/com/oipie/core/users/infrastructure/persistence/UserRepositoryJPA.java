package com.oipie.core.users.infrastructure.persistence;


import com.oipie.core.users.infrastructure.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryJPA extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findOneByEmail(String email);

    Optional<UserEntity> findOneByNickname(String nickname);
}
