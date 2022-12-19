package com.oipie.core.shared.infrastructure.errors;

import com.oipie.core.shared.domain.DomainError;
import com.oipie.core.shared.domain.DomainErrorCode;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public record ApiError(
        HttpStatus httpStatus,
        LocalDateTime timestamp,
        DomainErrorCode domainErrorCode,
        String message) {

    private static final Map<DomainErrorCode, HttpStatus> DOMAIN_ERROR_CODE_HTTP_STATUS_MAP = new HashMap<>() {{
        put(DomainErrorCode.EMAIL_ALREADY_IN_USE, HttpStatus.BAD_REQUEST);
        put(DomainErrorCode.INVALID_ID, HttpStatus.BAD_REQUEST);
        put(DomainErrorCode.INVALID_EMAIL, HttpStatus.BAD_REQUEST);
        put(DomainErrorCode.INVALID_PASSWORD, HttpStatus.BAD_REQUEST);
        put(DomainErrorCode.INVALID_JWT, HttpStatus.UNAUTHORIZED);
        put(DomainErrorCode.LOGIN_FAILED, HttpStatus.UNAUTHORIZED);
        put(DomainErrorCode.NICKNAME_ALREADY_IN_USE, HttpStatus.BAD_REQUEST);
        put(DomainErrorCode.RECIPE_NOT_FOUND, HttpStatus.NOT_FOUND);
        put(DomainErrorCode.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }};

    public static ApiError create(DomainError domainError, LocalDateTime timestamp) {
        return new ApiError(DOMAIN_ERROR_CODE_HTTP_STATUS_MAP.get(domainError.getCode()),
                timestamp,
                domainError.getCode(),
                domainError.getMessage());
    }


}
