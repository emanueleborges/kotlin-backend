package com.example

import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Configuration for OpenAPI/Swagger documentation
 */
@Configuration
class OpenApiConfig {

    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
            .info(apiInfo())
            .externalDocs(externalDocumentation())
            .addServersItem(Server().url("/").description("Default Server URL"))
    }

    private fun apiInfo(): Info {
        return Info()
            .title("Kotlin Backend API")
            .description("REST API for User Management built with Kotlin and Spring Boot")
            .version("1.0.0")
            .contact(
                Contact()
                    .name("API Support")
                    .url("https://example.com/support")
                    .email("support@example.com")
            )
            .license(
                License()
                    .name("MIT License")
                    .url("https://opensource.org/licenses/MIT")
            )
    }

    private fun externalDocumentation(): ExternalDocumentation {
        return ExternalDocumentation()
            .description("Project Documentation")
            .url("https://example.com/docs")
    }
}
