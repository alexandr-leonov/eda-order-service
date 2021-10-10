package ru.edaproject.leonov.orderservice.config

import io.swagger.v3.oas.annotations.enums.ParameterIn
import kotlinx.coroutines.FlowPreview
import org.springdoc.core.fn.builders.parameter.Builder
import org.springdoc.kotlin.docRouter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.config.ResourceHandlerRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import ru.edaproject.leonov.orderservice.router.OrderRouter
import ru.edaproject.leonov.orderservice.router.StoreRouter
import ru.edaproject.leonov.orderservice.service.OrderService

@Configuration
class WebConfig : WebFluxConfigurer {

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/swagger-ui.html**")
                .addResourceLocations("classpath:/META-INF/resources/")

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
    }

    @Bean
    @FlowPreview
    fun orderRoute(orderRouter: OrderRouter): RouterFunction<ServerResponse> = docRouter {
        GET("/user/{userId}/orders", accept(MediaType.APPLICATION_JSON), orderRouter::showUserOrders)
        {
            it.operationId("showUserOrders")
                    .beanClass(OrderService::class.java)
                    .beanMethod( "showUserOrders")
                    .parameter(Builder.parameterBuilder().name("userId").`in`(ParameterIn.PATH))
        }
    }

    @Bean
    @FlowPreview
    fun storeRoute(storeRouter: StoreRouter): RouterFunction<ServerResponse> = docRouter {
        GET("/stores", accept(MediaType.APPLICATION_JSON), storeRouter::showStores)
        {
            it.operationId("showStores")
                    .beanClass(OrderService::class.java)
                    .beanMethod( "showStores")
        }
    }

}