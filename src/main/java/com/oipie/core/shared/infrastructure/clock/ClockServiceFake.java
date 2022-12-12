package com.oipie.core.shared.infrastructure.clock;

import com.oipie.core.shared.domain.ClockService;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;

public class ClockServiceFake implements ClockService {

    public static final LocalDateTime FIXED_FAKE_TIME = LocalDateTime.of(1998, Month.JANUARY, 14, 0, 0);
    private final LocalDateTime fixedFakeTime;

    public ClockServiceFake(LocalDateTime fixedFakeTime) {
        this.fixedFakeTime = fixedFakeTime;
    }

    public ClockServiceFake() {
        this.fixedFakeTime = FIXED_FAKE_TIME;
    }


    @Override
    public LocalDateTime getTime() {
        return this.fixedFakeTime;
    }

    @Override
    public long getTimeStamp() {
        return this.fixedFakeTime.toEpochSecond(ZoneOffset.UTC);
    }
}
