package com.example.cars.domain.models

sealed class Item
data class CarItem(
    val id: Int = UNDEFINED_ID,
    val manufacturer: String,
    val carModel: String
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}
