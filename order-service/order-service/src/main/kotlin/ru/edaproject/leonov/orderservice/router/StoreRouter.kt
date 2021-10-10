package ru.edaproject.leonov.orderservice.router

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import ru.edaproject.leonov.orderservice.model.extension.toResponse
import ru.edaproject.leonov.orderservice.service.OrderService

@Component
class StoreRouter(val orderService: OrderService) : AbstractRouter() {

    fun showStores(request: ServerRequest): Mono<ServerResponse> {
        log.debug("show stores")
        return orderService
                .showStores()
                .toResponse()
    }

}