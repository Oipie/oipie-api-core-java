package com.oipie.core.recipes.domain;

import com.oipie.core.recipes.domain.primitives.LikesPrimitives;
import com.oipie.core.shared.domain.ddd.ValueObject;
import com.oipie.core.users.domain.User;

import java.util.Set;
import java.util.stream.Collectors;

public final class Likes extends ValueObject {

    private final Set<User> users;

    private Likes(Set<User> users) {
        this.users = users;
    }

    public static Likes create() {
        return new Likes(Set.of());
    }

    public static Likes fromPrimitives(LikesPrimitives likesPrimitives) {
        return new Likes(likesPrimitives.users().stream().map(User::fromPrimitives).collect(Collectors.toSet()));
    }

    public void addLike(User user) {
        this.users.add(user);
    }

    public long getLikesAmount() {
        return this.users.size();
    }

    public LikesPrimitives toPrimitives() {
        return new LikesPrimitives(this.users.stream().map(User::toPrimitives).collect(Collectors.toSet()));
    }
}
