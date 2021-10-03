package ru.edaproject.leonov.orderservice.dao.impl

import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Component
import ru.edaproject.leonov.orderservice.dao.OrderDao
import ru.edaproject.leonov.orderservice.dao.repository.OrderRepository
import ru.edaproject.leonov.orderservice.model.entity.Order

@Component
class OrderDaoImpl(val orderRepository: OrderRepository) : OrderDao {
    override fun create(orders: Set<Order>): Flow<Order> = orderRepository.saveAll(orders)

    override fun showAllByUserId(userId: Long): Flow<Order> = orderRepository.findAllByUserId(userId)
}