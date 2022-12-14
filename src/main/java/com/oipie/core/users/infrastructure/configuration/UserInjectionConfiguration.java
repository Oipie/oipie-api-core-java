package com.oipie.core.users.infrastructure.configuration;

import com.oipie.core.users.domain.UserRepository;
import com.oipie.core.users.infrastructure.persistence.UserRepositoryJPA;
import com.oipie.core.users.infrastructure.persistence.UserRepositoryPostgres;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserInjectionConfiguration {

    @Bean
    UserRepository userRepository(UserRepositoryJPA repository) {
        return new UserRepositoryPostgres(repository);
    }
}
