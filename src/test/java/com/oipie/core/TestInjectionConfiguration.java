package com.oipie.core;

import com.oipie.core.shared.domain.ClockService;
import com.oipie.core.shared.domain.IdentificationService;
import com.oipie.core.shared.infrastructure.clock.ClockServiceFake;
import com.oipie.core.shared.infrastructure.identification.IdentificationServiceFake;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestInjectionConfiguration {
    @Primary
    @Bean
    @ConditionalOnProperty("test.clockService")
    public static ClockService clockFakeService() {
        return new ClockServiceFake();
    }

    @Primary
    @Bean
    @ConditionalOnProperty("test.uuidService")
    public static IdentificationService uuidFakeService() {
        return new IdentificationServiceFake();
    }
}

