package ru.edaproject.leonov.orderservice.dao

import kotlinx.coroutines.flow.Flow
import ru.edaproject.leonov.orderservice.model.entity.Order

interface OrderDao {

    fun create(orders: Set<Order>) : Flow<Order>

    fun showAllByUserId(userId: Long) : Flow<Order>

}