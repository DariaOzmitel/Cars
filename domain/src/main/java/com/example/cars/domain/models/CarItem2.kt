package com.example.cars.domain.models

data class CarItem2(
    val id: Int = UNDEFINED_ID,
    val manufacturer: String,
    val carModel: String
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}
