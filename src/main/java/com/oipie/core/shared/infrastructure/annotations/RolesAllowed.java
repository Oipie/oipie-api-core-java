package com.oipie.core.shared.infrastructure.annotations;

import com.oipie.core.shared.infrastructure.auth.Roles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RolesAllowed {
    Roles[] value() default {Roles.USER};
}
