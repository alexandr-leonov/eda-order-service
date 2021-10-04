package ru.edaproject.leonov.orderservice.model.dto

import java.math.BigDecimal

data class OrderCreateRequest(val userId: Long,
                              val storeId: String,
                              val name: String,
                              val amount: BigDecimal,
                              var description: String? = "") {
    override fun toString(): String {
        return "OrderCreateRequest(userId=$userId, storeId='$storeId', name='$name', amount=$amount, description=$description)"
    }
}