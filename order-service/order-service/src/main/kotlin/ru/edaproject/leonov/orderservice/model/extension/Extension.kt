package ru.edaproject.leonov.orderservice.model.extension

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactor.asFlux
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import ru.edaproject.leonov.orderservice.model.dto.OrderCreateRequest
import ru.edaproject.leonov.orderservice.model.dto.OrderResponse
import ru.edaproject.leonov.orderservice.model.dto.StoreResponse
import ru.edaproject.leonov.orderservice.model.entity.Order
import ru.edaproject.leonov.orderservice.model.entity.Store

// System

inline fun <reified T> T.logger(): Logger = LoggerFactory.getLogger(T::class.java)

fun <T: Any> Flow<T>.toResponse(): Mono<ServerResponse> =
        this.asFlux()
                .collectList()
                .flatMap { return@flatMap ServerResponse.ok().bodyValue(it) }

// Application

fun OrderCreateRequest.toEntity(): Order = Order(null, userId, storeId, name, amount, description)

fun Order.toModel(): OrderResponse = OrderResponse(orderId!!, userId, storeId, name, amount, description, state!!)

fun Store.toModel(): StoreResponse = StoreResponse(storeId!!, name)