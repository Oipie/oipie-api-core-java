package com.oipie.core.shared.infrastructure.identification;

import com.oipie.core.shared.domain.IDService;

public class IDServiceFake implements IDService {

    private int currentNumber;
    private static final String INITIAL_UUID = "00000000-0000-0000-0000-0000000000";

    public IDServiceFake(int currentNumber) {
        this.currentNumber = currentNumber;
    }

    public IDServiceFake() {
        this.currentNumber = 0;
    }

    public String generateID() {
        String uuid = INITIAL_UUID + String.format("%02d", currentNumber);
        currentNumber++;
        return uuid;
    }
}
