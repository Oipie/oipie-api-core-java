package com.oipie.core.shared.domain;

import com.oipie.core.shared.domain.ddd.ValueObject;
import com.oipie.core.shared.domain.errors.InvalidId;

import java.util.UUID;

public class DomainId extends ValueObject {

    private final UUID domainId;

    protected DomainId(UUID domainId) {
        this.domainId = domainId;
    }


    public static boolean isValid(String id) throws DomainError {
        try {
            UUID.fromString(id);
            return true;
        } catch (Exception err) {
            throw new InvalidId(id);
        }
    }

    @Override
    public String toString() {
        return this.domainId.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DomainId that)) return false;
        return that.toString().equals(this.toString());
    }

    @Override
    public int hashCode() {
        return this.domainId.hashCode();
    }
}
