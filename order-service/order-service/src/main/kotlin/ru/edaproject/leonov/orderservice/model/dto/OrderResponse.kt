package ru.edaproject.leonov.orderservice.model.dto

import ru.edaproject.leonov.orderservice.model.list.OrderState
import java.math.BigDecimal

data class OrderResponse(val orderId: String,
                         val userId: Long,
                         val storeId: String,
                         val name: String,
                         val amount: BigDecimal,
                         var description: String? = "",
                         val state: OrderState) {
    override fun toString(): String {
        return "OrderResponse(orderId='$orderId', " +
                "userId=$userId, " +
                "storeId='$storeId', " +
                "name='$name', " +
                "amount=$amount, " +
                "description=$description, " +
                "state=$state)"
    }
}