package ru.edaproject.leonov.orderservice.dao

import kotlinx.coroutines.flow.Flow
import ru.edaproject.leonov.orderservice.model.entity.Store

interface StoreDao {

    fun showAll() : Flow<Store>

}