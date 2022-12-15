package com.oipie.core.shared.infrastructure.rest.dtos;

import java.util.List;

public record PageResultDTO<T>(
        List<T> result,
        int page,
        int limit,
        long totalResults) {
}
