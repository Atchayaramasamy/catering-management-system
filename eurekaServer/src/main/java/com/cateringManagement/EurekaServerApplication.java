package com.cateringManagement;

// Import necessary Spring Boot and Netflix Eureka packages
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication  // Indicates a Spring Boot application
@EnableEurekaServer     // Enables this application to act as a Eureka server
public class EurekaServerApplication {

    // Main method to run the Spring Boot application
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);  // Starts the Eureka server
    }

}
