package com.example.cars.di

import androidx.lifecycle.ViewModel
import com.example.cars.activities.CarItemViewModel
import com.example.cars.activities.ManufacturerItemViewModel
import com.example.cars.fragments.CarListViewModel
import com.example.cars.fragments.ManufacturerListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @IntoMap
    @ViewModelKey(CarListViewModel::class)
    @Binds
    fun bindCarListViewModel(impl: CarListViewModel): ViewModel

    @IntoMap
    @ViewModelKey(CarItemViewModel::class)
    @Binds
    fun bindCarItemViewModel(impl: CarItemViewModel): ViewModel

    @IntoMap
    @ViewModelKey(ManufacturerListViewModel::class)
    @Binds
    fun bindManufacturerListViewModel(impl: ManufacturerListViewModel): ViewModel

    @IntoMap
    @ViewModelKey(ManufacturerItemViewModel::class)
    @Binds
    fun bindManufacturerItemViewModel(impl: ManufacturerItemViewModel): ViewModel
}