package com.oipie.core.shared.infrastructure.clock;

import com.oipie.core.shared.domain.ClockService;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class ClockServiceVanilla implements ClockService {

    @Override
    public LocalDateTime getTime() {
        return LocalDateTime.now();
    }

    @Override
    public long getTimeStamp() {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
    }
}
