package ru.edaproject.leonov.orderservice.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("Store")
data class Store(@Id
                 var storeId: String? = null,
                 val name: String,
                 val phone: String)