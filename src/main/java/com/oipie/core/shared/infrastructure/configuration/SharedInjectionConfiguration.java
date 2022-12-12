package com.oipie.core.shared.infrastructure.configuration;


import com.oipie.core.shared.domain.ClockService;
import com.oipie.core.shared.domain.IDService;
import com.oipie.core.shared.infrastructure.clock.ClockServiceVanilla;
import com.oipie.core.shared.infrastructure.identification.IDServiceVanilla;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SharedInjectionConfiguration {
    @Bean
    public static ClockService clockService() {
        return new ClockServiceVanilla();
    }

    @Bean
    public static IDService uuidService() {
        return new IDServiceVanilla();
    }
}
