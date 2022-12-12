package com.oipie.core.shared.domain;

import java.time.LocalDateTime;

public interface ClockService {
    LocalDateTime getTime();

    long getTimeStamp();
}
