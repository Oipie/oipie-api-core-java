package com.oipie.core.recipes.infrastructure.configuration;

import com.oipie.core.recipes.domain.RecipeRepository;
import com.oipie.core.recipes.infrastructure.persistence.RecipeRepositoryJPA;
import com.oipie.core.recipes.infrastructure.persistence.RecipeRepositoryPostgres;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RecipeInjectionConfiguration {

    @Bean
    RecipeRepository recipeRepository(RecipeRepositoryJPA repository) {
        return new RecipeRepositoryPostgres(repository);
    }
}
