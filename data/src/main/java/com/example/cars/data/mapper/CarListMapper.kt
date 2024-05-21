package com.example.cars.data.mapper

import com.example.cars.data.database.cars.CarInfoDbModel
import com.example.cars.domain.models.CarItem
import javax.inject.Inject

class CarListMapper @Inject constructor() {
    fun mapEntityToDbModel(carItem: CarItem) = CarInfoDbModel(
        id = carItem.id,
        manufacturer = carItem.manufacturer,
        carModel = carItem.carModel
    )

    fun mapDbModelToEntity(carInfoDbModel: CarInfoDbModel) = CarItem(
        id = carInfoDbModel.id,
        manufacturer = carInfoDbModel.manufacturer,
        carModel = carInfoDbModel.carModel
    )

    fun mapListDbToListEntity(list: List<CarInfoDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}