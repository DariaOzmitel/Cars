package com.example.cars.data

import com.example.cars.domain.models.CarItem
import com.example.cars.domain.repository.CarRepository
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(

): CarRepository {
    override fun getCarList(): List<CarItem> {
        TODO("Not yet implemented")
    }

    override fun addCar(carItem: CarItem) {
        TODO("Not yet implemented")
    }

    override fun editCar(carItem: CarItem) {
        TODO("Not yet implemented")
    }

    override fun deleteCar(carItem: CarItem) {
        TODO("Not yet implemented")
    }

    override fun getCarItem(carItemId: Int): CarItem {
        TODO("Not yet implemented")
    }
}