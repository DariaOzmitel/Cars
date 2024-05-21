package com.example.cars.domain.models

data class CarModelItem2(
    val id: Int = UNDEFINED_ID,
    val manufacturerId: Int,
    val carModelName: String
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}