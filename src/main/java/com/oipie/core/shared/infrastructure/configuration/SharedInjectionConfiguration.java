package com.oipie.core.shared.infrastructure.configuration;


import com.oipie.core.shared.domain.AuthorizationService;
import com.oipie.core.shared.domain.ClockService;
import com.oipie.core.shared.domain.IdentificationService;
import com.oipie.core.shared.infrastructure.auth.SpringAuthorizationService;
import com.oipie.core.shared.infrastructure.clock.ClockServiceVanilla;
import com.oipie.core.shared.infrastructure.identification.IdentificationServiceVanilla;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SharedInjectionConfiguration {
    @Bean
    public static ClockService clockService() {
        return new ClockServiceVanilla();
    }

    @Bean
    public static IdentificationService uuidService() {
        return new IdentificationServiceVanilla();
    }


    @Bean
    public SecurityFilterChain authConfiguration(HttpSecurity http) throws Exception {
        //Open all endpoints for now
        http.csrf().disable().authorizeRequests().anyRequest().permitAll();
        return http.build();
    }

    @Bean
    public AuthorizationService authorizationService() {
        return new SpringAuthorizationService();
    }

}
