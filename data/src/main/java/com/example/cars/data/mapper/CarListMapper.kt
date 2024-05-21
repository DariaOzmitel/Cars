package com.example.cars.data.mapper

import com.example.cars.data.database.cars.CarInfoDbModel
import com.example.cars.domain.models.CarItem
import javax.inject.Inject

class CarListMapper @Inject constructor() {

    fun mapCarEntityToDbModel(item: CarItem) = CarInfoDbModel(
        id = item.id,
        manufacturer = item.manufacturer,
        carModel = item.carModel
    )

    fun mapCarDbModelToEntity(carInfoDbModel: CarInfoDbModel) = CarItem(
        id = carInfoDbModel.id,
        manufacturer = carInfoDbModel.manufacturer,
        carModel = carInfoDbModel.carModel
    )

    fun mapListDbToListEntity(list: List<CarInfoDbModel>) = list.map {
        mapCarDbModelToEntity(it)
    }
}