package com.oipie.core.users.infrastructure.rest.dtos;

import javax.validation.constraints.NotBlank;

public record CreateUserRequestDTO(
        @NotBlank
        String email,
        @NotBlank
        String nickname,
        @NotBlank
        String password) {
}
