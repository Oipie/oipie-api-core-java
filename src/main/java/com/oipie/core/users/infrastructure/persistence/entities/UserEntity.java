package com.oipie.core.users.infrastructure.persistence.entities;


import com.oipie.core.recipes.infrastructure.persistence.entities.RecipeEntity;
import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.users.domain.User;
import com.oipie.core.users.domain.primitives.UserPrimitives;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(mappedBy = "likesByUsers")
    private Set<RecipeEntity> likes;

    public static UserEntity fromDomain(User user) {
        return UserEntity.fromPrimitives(user.toPrimitives());
    }

    public static UserEntity fromPrimitives(UserPrimitives userPrimitives) {
        UserEntity userEntity = new UserEntity();
        userEntity.userId = userPrimitives.userId();
        userEntity.email = userPrimitives.email();
        userEntity.nickname = userPrimitives.nickname();
        userEntity.password = userPrimitives.password();
        return userEntity;
    }

    public User toDomain() throws DomainError {
        return User.fromPrimitives(this.toPrimitives());
    }

    public UserPrimitives toPrimitives() throws DomainError {
        return new UserPrimitives(
                userId,
                email,
                nickname,
                password
        );
    }
}
