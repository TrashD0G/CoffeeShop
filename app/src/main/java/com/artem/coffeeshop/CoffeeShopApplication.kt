package com.artem.coffeeshop

import android.app.Application
import com.artem.coffeeshop.di.AccountComponent
import com.artem.coffeeshop.di.DaggerAccountComponent

class CoffeeShopApplication : Application() {

    lateinit var applicationAccountComponent: AccountComponent
        private set

    override fun onCreate() {
        super.onCreate()
        applicationAccountComponent = DaggerAccountComponent.create()
    }
}