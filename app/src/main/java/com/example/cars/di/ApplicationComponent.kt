package com.example.cars.di

import android.app.Application
import com.example.cars.activities.CarItemActivity
import com.example.cars.activities.MainActivity
import com.example.cars.activities.ManufacturerItemActivity
import com.example.cars.fragments.CarListFragment
import com.example.cars.fragments.ManufacturerListFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)
    fun inject(activity: CarItemActivity)
    fun inject(activity: ManufacturerItemActivity)
    fun inject(fragment: CarListFragment)
    fun inject(fragment: ManufacturerListFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            application: Application
        ): ApplicationComponent
    }
}