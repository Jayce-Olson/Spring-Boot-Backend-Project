package com.example.demo.config;

import com.example.demo.entities.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <h1>RestDataConfig</h1>
 * Configures the REST API endpoints exposed for the project.
 *
 * @author WGU Course Materials
 * @version 0.1
 * @since 2023-02-27
 */
@Configuration
public class RestDataConfig implements RepositoryRestConfigurer, WebMvcConfigurer {

    /**
     * Configures CORS and exposes entity IDs.
     *
     * @param config Repository configuration for exposed endpoints.
     * @param cors   CORS registry for allowed origins.
     */
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Country.class);
        config.exposeIdsFor(Customer.class);
        config.exposeIdsFor(Division.class);
        config.exposeIdsFor(Excursion.class);
        config.exposeIdsFor(Vacation.class);
        config.setDefaultPageSize(Integer.MAX_VALUE);
        config.setMaxPageSize(Integer.MAX_VALUE);
        cors.addMapping("/**").allowedOrigins("http://localhost:4200");
    }
    @Override // I had to add this because the frontend has a url path with a trailing slash that was causing errors. It ended up being a @Data annotation problem, but I decided to leave this just in case
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/api/excursions/", "/api/excursions");
    }
}

