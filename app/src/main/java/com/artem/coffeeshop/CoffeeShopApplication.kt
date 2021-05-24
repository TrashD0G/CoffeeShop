package com.artem.coffeeshop

import android.app.Application
import com.artem.coffeeshop.di.AppComponent
import com.artem.coffeeshop.di.DaggerAppComponent

class CoffeeShopApplication : Application() {

    lateinit var applicationAppComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        applicationAppComponent = DaggerAppComponent.create()
    }
}