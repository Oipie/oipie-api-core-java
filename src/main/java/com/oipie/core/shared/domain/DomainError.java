package com.oipie.core.shared.domain;

public class DomainError extends Exception {

    private final DomainErrorCode code;


    public DomainError(DomainErrorCode code, String message) {
        super(message);
        this.code = code;
    }


    public DomainErrorCode getCode() {
        return this.code;
    }
}
