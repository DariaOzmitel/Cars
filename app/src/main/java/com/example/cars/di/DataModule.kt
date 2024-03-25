package com.example.cars.di

import android.app.Application
import com.example.cars.data.CarRepositoryImpl
import com.example.cars.data.database.AppDatabase
import com.example.cars.data.database.CarInfoDao
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
    }
}