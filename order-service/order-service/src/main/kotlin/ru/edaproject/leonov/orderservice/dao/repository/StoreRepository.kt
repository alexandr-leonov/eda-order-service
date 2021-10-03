package ru.edaproject.leonov.orderservice.dao.repository

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import ru.edaproject.leonov.orderservice.model.entity.Store

@Repository
interface StoreRepository : CoroutineCrudRepository<Store, String>