package com.oipie.core.shared.infrastructure.identification;

import com.oipie.core.shared.domain.IdentificationService;

public class IdentificationServiceFake implements IdentificationService {

    private static final String INITIAL_UUID = "00000000-0000-0000-0000-0000000000";
    private int currentNumber;

    public IdentificationServiceFake(int currentNumber) {
        this.currentNumber = currentNumber;
    }

    public IdentificationServiceFake() {
        this.currentNumber = 0;
    }

    public String generateID() {
        String uuid = INITIAL_UUID + String.format("%02d", currentNumber);
        currentNumber++;
        return uuid;
    }
}
