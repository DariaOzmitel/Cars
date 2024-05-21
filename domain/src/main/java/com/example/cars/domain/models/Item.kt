package com.example.cars.domain.models

sealed class Item
data class CarItem(
    val id: Int = UNDEFINED_ID,
    val manufacturer: String,
    val carModel: String
) : Item()

data class CarModelItem(
    val id: Int = UNDEFINED_ID,
    val manufacturerId: Int,
    val carModelName: String
) : Item()

data class ManufacturerItem(
    val id: Int = ManufacturerItem2.UNDEFINED_ID,
    val manufacturerName: String
) : Item()

const val UNDEFINED_ID = 0
