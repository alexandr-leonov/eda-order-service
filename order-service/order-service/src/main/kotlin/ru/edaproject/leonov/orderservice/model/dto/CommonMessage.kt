package ru.edaproject.leonov.orderservice.model.dto

data class CommonMessage(val id: String) {
    override fun toString(): String {
        return "CommonMessage(id='$id')"
    }
}
