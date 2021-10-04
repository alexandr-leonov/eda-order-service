package ru.edaproject.leonov.orderservice.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import ru.edaproject.leonov.orderservice.model.list.OrderState
import java.math.BigDecimal

@Table("ORDERS")
data class Order(@Id
                 var orderId: String? = null,
                 val userId: Long,
                 val storeId: String,
                 val name: String,
                 val amount: BigDecimal,
                 var description: String? = "",
                 var state: OrderState? = OrderState.NEW)