package com.example.cars.di

import androidx.lifecycle.ViewModel
import com.example.cars.activities.CarItemViewModel
import com.example.cars.fragments.CarListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @IntoMap
    @ViewModelKey(CarListViewModel::class)
    @Binds
    fun bindMainViewModel(impl: CarListViewModel): ViewModel

    @IntoMap
    @ViewModelKey(CarItemViewModel::class)
    @Binds
    fun bindCarItemViewModel(impl: CarItemViewModel): ViewModel
}