package com.gatewayapi.gatewayservice.configuration;

// Import necessary Spring Cloud Gateway packages
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // Indicates that this class contains Spring configuration
public class GatewayConfig {

    // Define a bean for custom route locator configuration
    @Bean
    RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("cateringmanagement", r -> r
                    .path("/menu/**")  // Route for menu service
                    .uri("http://localhost:8010"))
            .route("cateringmanagement", r -> r
                    .path("/users/**")  // Route for users service
                    .uri("http://localhost:8010"))
            .route("cateringmanagement", r -> r
                    .path("/orders/**")  // Route for orders service
                    .uri("http://localhost:8010"))
            .route("cateringmanagement", r -> r
                    .path("/orderdetails/**")  // Route for order details service
                    .uri("http://localhost:8010"))
            .build();  // Build and return the route locator
    }
}
