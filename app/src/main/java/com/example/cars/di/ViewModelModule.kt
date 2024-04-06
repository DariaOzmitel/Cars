package com.example.cars.di

import androidx.lifecycle.ViewModel
import com.example.cars.CarItemViewModel
import com.example.cars.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Binds
    fun bindMainViewModel(impl: MainViewModel): ViewModel

    @IntoMap
    @ViewModelKey(CarItemViewModel::class)
    @Binds
    fun bindCarItemViewModel(impl: CarItemViewModel): ViewModel
}