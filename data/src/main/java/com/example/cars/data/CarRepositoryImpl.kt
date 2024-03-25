package com.example.cars.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.cars.data.database.CarInfoDao
import com.example.cars.domain.models.CarItem
import com.example.cars.domain.repository.CarRepository
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(
    private val carInfoDao: CarInfoDao,
    private val mapper: CarListMapper
): CarRepository {
    override fun getCarList(): LiveData<List<CarItem>> = carInfoDao.getCarList().map{
        mapper.mapListDbToListEntity(it)
    }

    override fun addCar(carItem: CarItem) {
        carInfoDao.addCarItem(mapper.mapEntityToDbModel(carItem))
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