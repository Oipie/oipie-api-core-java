package com.oipie.core.users.infrastructure.rest.dtos;

import javax.validation.constraints.NotBlank;

public record LoginUserRequestDTO(
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
