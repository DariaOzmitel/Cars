package com.example.cars

import android.app.Application
import com.example.cars.di.DaggerApplicationComponent

class CarApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }
}