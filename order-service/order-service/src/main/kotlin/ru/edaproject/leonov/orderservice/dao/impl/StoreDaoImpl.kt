package ru.edaproject.leonov.orderservice.dao.impl

import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Component
import ru.edaproject.leonov.orderservice.dao.StoreDao
import ru.edaproject.leonov.orderservice.dao.repository.StoreRepository
import ru.edaproject.leonov.orderservice.model.entity.Store

@Component
class StoreDaoImpl(val storeRepository: StoreRepository) : StoreDao {
    override fun showAll(): Flow<Store> = storeRepository.findAll()
}