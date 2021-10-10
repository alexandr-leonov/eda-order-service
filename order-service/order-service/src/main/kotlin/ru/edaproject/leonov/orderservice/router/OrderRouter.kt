package ru.edaproject.leonov.orderservice.router

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import ru.edaproject.leonov.orderservice.model.extension.toResponse
import ru.edaproject.leonov.orderservice.service.OrderService

@Component
class OrderRouter(val orderService: OrderService) : AbstractRouter() {

    fun showUserOrders(request: ServerRequest): Mono<ServerResponse> {
        val userId: Long = request.pathVariable("userId").toLong()
        log.debug("showUserOrders by userId = $userId")
        return orderService
                .showUserOrders(userId)
                .toResponse()
    }

}