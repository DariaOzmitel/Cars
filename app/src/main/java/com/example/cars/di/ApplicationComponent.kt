package com.example.cars.di

import android.app.Application
import com.example.cars.CarItemActivity
import com.example.cars.CarListFragment
import com.example.cars.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)
    fun inject(activity: CarItemActivity)
    fun inject(fragment: CarListFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            application: Application
        ): ApplicationComponent
    }
}