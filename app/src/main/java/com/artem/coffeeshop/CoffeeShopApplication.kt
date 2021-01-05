package com.artem.coffeeshop

import android.app.Application
import com.artem.coffeeshop.di.CreateAccountComponent
import com.artem.coffeeshop.di.DaggerCreateAccountComponent

class CoffeeShopApplication : Application() {

    lateinit var applicationCreateAccountComponent: CreateAccountComponent
        private set

    override fun onCreate() {
        super.onCreate()
        applicationCreateAccountComponent = DaggerCreateAccountComponent.create()
    }
}