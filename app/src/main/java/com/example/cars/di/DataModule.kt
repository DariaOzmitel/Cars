package com.example.cars.di

import android.app.Application
import com.example.cars.data.repository.CarRepositoryImpl
import com.example.cars.data.database.AppDatabase
import com.example.cars.data.database.cars.CarInfoDao
import com.example.cars.data.database.manufacturers.ManufacturerInfoDao
import com.example.cars.domain.repository.CarRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindRepository(impl: CarRepositoryImpl): CarRepository

    companion object {
        @Provides
        fun provideCarInfoDao(application: Application): CarInfoDao {
            return AppDatabase.getInstance(application).carInfoDao()
        }

        @Provides
        fun provideManufacturerInfoDao(application: Application): ManufacturerInfoDao {
            return AppDatabase.getInstance(application).manufacturerInfoDao()
        }
    }
}