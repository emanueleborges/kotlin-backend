// Application.kt
package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * Main Spring Boot application class
 * 
 * This class serves as the entry point for the Spring Boot application and provides
 * configuration for various application-wide concerns such as CORS, security, etc.
 */
@SpringBootApplication
@ComponentScan(basePackages = ["com.example"])
class CrudApplication {
    
    /**
     * Configure CORS for the application
     * 
     * This configuration allows cross-origin requests from specified origins,
     * which is essential for frontend applications communicating with this API.
     * 
     * @return WebMvcConfigurer with CORS configuration
     */
    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/api/**")
                    .allowedOrigins("http://localhost:8080") // Add your frontend URL
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*")
                    .allowCredentials(true)
                    .maxAge(3600) // 1 hour cache for preflight requests
            }
        }
    }
}

/**
 * Application entry point
 * 
 * This function starts the Spring Boot application.
 * 
 * @param args Command line arguments
 */
fun main(args: Array<String>) {
    // Simple startup without advanced configuration
    runApplication<CrudApplication>(*args)
}
