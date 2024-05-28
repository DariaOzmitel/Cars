package com.example.cars.data.mapper

import com.example.cars.data.database.carModel.CarModelInfoDbModel
import com.example.cars.domain.models.CarModelItem
import javax.inject.Inject

class CarModelListMapper @Inject constructor() {

    fun mapEntityToDbModel(item: CarModelItem) = CarModelInfoDbModel(
        id = item.id,
        manufacturerName = item.manufacturerName,
        carModel = item.carModelName
    )

    fun mapDbModelToEntity(carModelInfoDbModel: CarModelInfoDbModel) = CarModelItem(
        id = carModelInfoDbModel.id,
        manufacturerName = carModelInfoDbModel.manufacturerName,
        carModelName = carModelInfoDbModel.carModel
    )

    fun mapListDbToListEntity(list: List<CarModelInfoDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}