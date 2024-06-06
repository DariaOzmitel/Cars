package com.example.cars.domain.models

sealed class Item {
    companion object {
        const val UNDEFINED_ID = 0
    }
}

data class CarItem(
    val id: Int = UNDEFINED_ID,
    val manufacturer: String,
    val carModel: String,
    val price: Int
) : Item()

data class CarModelItem(
    val id: Int = UNDEFINED_ID,
    val manufacturerName: String,
    val carModelName: String
) : Item()

data class ManufacturerItem(
    val id: Int = UNDEFINED_ID,
    val manufacturerName: String
) : Item()
