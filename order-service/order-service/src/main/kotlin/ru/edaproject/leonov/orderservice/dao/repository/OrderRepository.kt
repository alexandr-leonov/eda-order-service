package ru.edaproject.leonov.orderservice.dao.repository

import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.edaproject.leonov.orderservice.model.entity.Order

@Repository
interface OrderRepository : CoroutineCrudRepository<Order, String> {

    // language=SQL
    @Query("""
        SELECT * 
        FROM ORDER 
        WHERE USER_ID = :userId
    """)
    fun findAllByUserId(@Param("userId") userId: Long) : Flow<Order>
}