package com.example.cars.domain.models

data class ManufacturerItem2(
    val id: Int = UNDEFINED_ID,
    val manufacturerName: String
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}
