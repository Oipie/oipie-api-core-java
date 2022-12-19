package com.oipie.core.recipes.domain.primitives;

import com.oipie.core.users.domain.primitives.UserPrimitives;

import java.util.Set;

public record LikesPrimitives(Set<UserPrimitives> users) {
}
