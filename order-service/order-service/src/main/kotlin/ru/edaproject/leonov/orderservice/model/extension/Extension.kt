package ru.edaproject.leonov.orderservice.model.extension

import ru.edaproject.leonov.orderservice.model.dto.OrderCreateRequest
import ru.edaproject.leonov.orderservice.model.dto.OrderResponse
import ru.edaproject.leonov.orderservice.model.dto.StoreResponse
import ru.edaproject.leonov.orderservice.model.entity.Order
import ru.edaproject.leonov.orderservice.model.entity.Store

fun OrderCreateRequest.toEntity(): Order = Order(null, userId, storeId, name, amount, description)

fun Order.toModel(): OrderResponse = OrderResponse(orderId!!, userId, storeId, name, amount, description, state!!)

fun Store.toModel(): StoreResponse = StoreResponse(storeId!!, name)