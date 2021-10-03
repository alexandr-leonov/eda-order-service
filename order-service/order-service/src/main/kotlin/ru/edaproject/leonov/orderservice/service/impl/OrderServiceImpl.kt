package ru.edaproject.leonov.orderservice.service.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.stereotype.Service
import ru.edaproject.leonov.orderservice.dao.OrderDao
import ru.edaproject.leonov.orderservice.dao.StoreDao
import ru.edaproject.leonov.orderservice.model.dto.OrderCreateRequest
import ru.edaproject.leonov.orderservice.model.dto.OrderResponse
import ru.edaproject.leonov.orderservice.model.dto.StoreResponse
import ru.edaproject.leonov.orderservice.model.entity.Order
import ru.edaproject.leonov.orderservice.model.extension.toEntity
import ru.edaproject.leonov.orderservice.model.extension.toModel
import ru.edaproject.leonov.orderservice.service.OrderService
import java.util.stream.Collectors
import java.util.stream.Stream

@Service
class OrderServiceImpl(val orderDao: OrderDao, val storeDao: StoreDao) : OrderService {
    override fun createOrder(request: OrderCreateRequest): Flow<OrderResponse> {
        val orders: Set<Order> = Stream
                .of(request.toEntity())
                .collect(Collectors.toUnmodifiableSet())
        return orderDao
                .create(orders)
                .map { it.toModel() }
    }

    override fun showStores(): Flow<StoreResponse> = storeDao
            .showAll()
            .map { it.toModel() }

    override fun showUserOrders(userId: Long): Flow<OrderResponse> = orderDao
            .showAllByUserId(userId)
            .map { it.toModel() }
}