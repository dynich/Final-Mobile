package com.d121211093.fruityvice

import android.app.Application
import com.d121211093.fruityvice.data.DefaultAppContainer
import com.d121211093.fruityvice.data.FruitViceContainer

class FruitViceApplication() : Application(){
    lateinit var container: FruitViceContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}