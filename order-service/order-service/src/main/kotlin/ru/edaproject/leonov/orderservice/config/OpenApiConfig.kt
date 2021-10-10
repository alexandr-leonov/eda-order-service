package ru.edaproject.leonov.orderservice.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
                .components(Components())
                .info(Info()
                        .title("Order service API")
                        .license(License()
                                .name("MIT License")
                                .url("https://github.com/alexandr-leonov/eda-order-service")
                        ))
    }

}