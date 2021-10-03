package ru.edaproject.leonov.orderservice.service

import kotlinx.coroutines.flow.Flow
import ru.edaproject.leonov.orderservice.model.dto.OrderCreateRequest
import ru.edaproject.leonov.orderservice.model.dto.OrderResponse
import ru.edaproject.leonov.orderservice.model.dto.StoreResponse

interface OrderService {

    fun createOrder(request: OrderCreateRequest) : Flow<OrderResponse>

    fun showStores() : Flow<StoreResponse>

    fun showUserOrders(userId: Long) : Flow<OrderResponse>

}